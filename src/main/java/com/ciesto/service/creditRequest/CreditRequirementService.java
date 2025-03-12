package com.ciesto.service.creditRequest;

import com.ciesto.model.creditRequirement.CreditRequirement;
import com.ciesto.model.creditRequirement.CompanyProfile;
import com.ciesto.model.creditRequirement.RequirementApplicantDetails;
import com.ciesto.repository.creditRequirement.CompanyProfileRepository;
import com.ciesto.repository.creditRequirement.CreditRequirementRepository;
import com.ciesto.repository.creditRequirement.RequirementApplicantDetailsRepository;
import jakarta.persistence.criteria.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.ciesto.common.Constants.*;


@Service
public class CreditRequirementService {

    private static final Logger logger = LogManager.getLogger(CreditRequirementService.class);

    @Autowired
    private CreditRequirementRepository creditRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CompanyProfileRepository companyRepository;


    @Autowired
    private RequirementApplicantDetailsRepository repository;

    @Autowired
    private CreditRequirementRepository creditRequirementRepository;



    public List<CreditRequirement> getAllCreditRequirements() {
        logger.info("Fetching all credit requirements with company details");
        return creditRepository.findAll();
    }

    public CreditRequirement addOrUpdateCreditRequirement(CreditRequirement creditRequirement) {
        logger.info("Processing credit requirement: {}", creditRequirement);

        if (creditRequirement.getCompany() == null || creditRequirement.getCompany().getPanNo() == null) {
            throw new IllegalArgumentException("Company details and PAN are required!");
        }

        String panNo = creditRequirement.getCompany().getPanNo();
        Optional<CompanyProfile> existingCompanyOpt = companyRepository.findByPanNo(panNo);

        CompanyProfile company;
        if (existingCompanyOpt.isPresent()) {
            company = existingCompanyOpt.get();
            logger.info("Updating existing company with PAN: {}", panNo);

            // Update company details
            company.setName(creditRequirement.getCompany().getName());
            company.setRegistrationType(creditRequirement.getCompany().getRegistrationType());
            company.setGstin(creditRequirement.getCompany().getGstin());
            company.setIndustry(creditRequirement.getCompany().getIndustry());
            company.setSector(creditRequirement.getCompany().getSector());
            company.setMsmeRegistrationNumber(creditRequirement.getCompany().getMsmeRegistrationNumber());
            company.setIncorporationDate(creditRequirement.getCompany().getIncorporationDate());
            company.setAddress(creditRequirement.getCompany().getAddress());
            company.setCity(creditRequirement.getCompany().getCity());
            company.setState(creditRequirement.getCompany().getState());
            company.setZip(creditRequirement.getCompany().getZip());
            company.setCountry(creditRequirement.getCompany().getCountry());
            company.setInsertedOrUpdatedDate(LocalDateTime.now());
        } else {
            logger.info("Creating a new company for PAN: {}", panNo);
            company = creditRequirement.getCompany();
            company.setInsertedOrUpdatedDate(LocalDateTime.now());
        }

        // Save or update company
        CompanyProfile savedCompany = companyRepository.save(company);
        creditRequirement.setCompany(savedCompany);

        // Save credit requirement
        CreditRequirement savedCreditRequirement = creditRepository.save(creditRequirement);
        logger.info("Credit requirement saved successfully: {}", savedCreditRequirement);

        return savedCreditRequirement;
    }


    public List<CreditRequirement> filterCreditRequirements(String companyName, String purpose,
                                                            String status, String identifiedOn,
                                                            String sourceChannel) {
        logger.info("Filtering credit requirements with criteria: companyName={}, purpose={}, status={}, identifiedOn={}, sourceChannel={}",
                companyName, purpose, status, identifiedOn, sourceChannel);

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CreditRequirement> query = cb.createQuery(CreditRequirement.class);
        Root<CreditRequirement> creditRoot = query.from(CreditRequirement.class);

        Join<CreditRequirement, CompanyProfile> companyJoin = creditRoot.join(COMPANY);

        List<Predicate> predicates = new ArrayList<>();

        if (companyName != null && !companyName.isEmpty()) {
            predicates.add(cb.like(cb.lower(companyJoin.get(NAME)), PERCENT_SYMBOL + companyName.replace(" ","").toLowerCase() + PERCENT_SYMBOL));
        }
        if (purpose != null && !purpose.isEmpty()) {
            predicates.add(cb.like(cb.lower(creditRoot.get(REQUIREMENT_DESCRIPTION)), PERCENT_SYMBOL + purpose.toLowerCase() + PERCENT_SYMBOL));
        }
        if (status != null && !status.isEmpty()) {
            predicates.add(cb.like(cb.lower(creditRoot.get(STATUS)), PERCENT_SYMBOL + status.toLowerCase() + PERCENT_SYMBOL));
        }
        if (identifiedOn != null && !identifiedOn.isEmpty()) {
            predicates.add(cb.like(cb.lower(creditRoot.get(DATE_OF_FUND_REQUIREMENT).as(String.class)), PERCENT_SYMBOL + identifiedOn + PERCENT_SYMBOL));
        }
        if (sourceChannel != null && !sourceChannel.isEmpty()) {
            predicates.add(cb.like(cb.lower(creditRoot.get(SOURCE_CHANNEL)), PERCENT_SYMBOL + sourceChannel.toLowerCase() + PERCENT_SYMBOL));
        }

        query.select(creditRoot).where(predicates.toArray(new Predicate[0]));

        List<CreditRequirement> results = entityManager.createQuery(query).getResultList();
        logger.info("Filtered records count: {}", results.size());

        return results;
    }

    public CreditRequirement saveCreditRequirement(CreditRequirement creditRequirement) {
        logger.info("Saving credit requirement: {}", creditRequirement);
        return creditRepository.save(creditRequirement);
    }

    /**
     * Deletes a credit requirement by ID.
     */
    public void deleteCreditRequirement(Long id) {
        logger.info("Deleting credit requirement with ID: {}", id);
        creditRepository.deleteById(id);
    }


    public RequirementApplicantDetails addRequirementApplicant(Long creditRequirementId, RequirementApplicantDetails applicant,
                                                               MultipartFile panCard, MultipartFile aadharCard, MultipartFile msmeRegistration) {
        CreditRequirement creditRequirement = creditRequirementRepository.findById(creditRequirementId)
                .orElseThrow(() -> new RuntimeException("Credit Requirement not found"));

        applicant.setCreditRequirement(creditRequirement);

        // Store only the file path in DB
//        applicant.setPanCard(saveFile(panCard));
//        applicant.setAadharCard(saveFile(aadharCard));
//        applicant.setMsmeRegistration(saveFile(msmeRegistration));

        return repository.save(applicant);
    }

    private static final String UPLOAD_DIR = "/uploads/documents/";


    private String saveFile(MultipartFile file) {
        if (file == null || file.isEmpty()) return null;
        try {
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String filePath = UPLOAD_DIR + file.getOriginalFilename();
            Files.write(Paths.get(filePath), file.getBytes());
            return filePath;
        } catch (Exception e) {
            throw new RuntimeException("File upload failed", e);
        }
    }

    public List<RequirementApplicantDetails> getApplicantsByCreditRequirement(Long creditRequirementId) {
        return repository.findByCreditRequirement_Id(creditRequirementId);
    }

    public RequirementApplicantDetails getApplicantById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Applicant not found"));
    }

    public RequirementApplicantDetails updateApplicant(Long id, RequirementApplicantDetails updatedApplicant) {
        return repository.findById(id).map(existing -> {
            existing.setFullName(updatedApplicant.getFullName());
            existing.setEmail(updatedApplicant.getEmail());
            existing.setPhone(updatedApplicant.getPhone());
            existing.setEmploymentType(updatedApplicant.getEmploymentType());
            existing.setIncomePerAnnum(updatedApplicant.getIncomePerAnnum());
            existing.setState(updatedApplicant.getState());
//            existing.setPanDocument(updatedApplicant.getPanDocument());
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Applicant not found"));
    }

    public void deleteApplicant(Long id) {
        repository.deleteById(id);
    }
}
