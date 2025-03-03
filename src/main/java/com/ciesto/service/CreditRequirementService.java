package com.ciesto.service;

import com.ciesto.model.CreditRequirement;
import com.ciesto.model.CompanyProfile;
import com.ciesto.repository.CompanyProfileRepository;
import com.ciesto.repository.CreditRequirementRepository;
import jakarta.persistence.criteria.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
            predicates.add(cb.like(cb.lower(companyJoin.get(NAME)), PERCENT_SYMBOL + companyName.toLowerCase() + PERCENT_SYMBOL));
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
}
