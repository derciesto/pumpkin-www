package com.ciesto.service.creditApplication;


import com.ciesto.common.Constants;
import com.ciesto.common.customException.ImproperDataException;
import com.ciesto.common.customException.ResourceNotFoundException;
import com.ciesto.model.creditRequirement.CompanyProfile;
import com.ciesto.model.creditApplication.CreditApplication;
import com.ciesto.model.creditRequirement.CreditRequirement;
import com.ciesto.repository.creditRequirement.CompanyProfileRepository;
import com.ciesto.repository.creditApplication.CreditApplicationRepository;
import com.ciesto.repository.creditRequirement.CreditRequirementRepository;
import com.ciesto.service.creditApplication.utility.CreditApplicationSpecification;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UnknownFormatConversionException;

@Service
public class CreditApplicationService {

    private final CreditApplicationRepository repository;

    private final CompanyProfileRepository companyProfileRepository;

    private final CreditRequirementRepository creditRequirementRepository;

    public CreditApplicationService(CreditApplicationRepository repository, CompanyProfileRepository companyProfileRepository, CreditRequirementRepository creditRequirementRepository) {
        this.repository = repository;
        this.companyProfileRepository = companyProfileRepository;
        this.creditRequirementRepository = creditRequirementRepository;
    }

    public CreditApplication createCreditApplication(Long companyId, CreditApplication creditApplication) {
        try {
            CompanyProfile company = companyProfileRepository.findById(companyId)
                    .orElseThrow(() -> new ResourceNotFoundException("Company not found", 404L));

            creditApplication.setCompany(company);
            return repository.save(creditApplication);
        } catch (DataIntegrityViolationException e) {
            throw new ImproperDataException("Provide proper data", Constants.DATA_NOT_FOUND, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw new UnknownFormatConversionException("Data can not be persisted");
        }
    }

    public List<CreditApplication> getAllFiltered(String companyName, String purpose, String identifiedOn, String sourceChannel, String status) {
        Specification<CreditApplication> spec = CreditApplicationSpecification.withFilters(companyName, purpose, identifiedOn, sourceChannel, status);
        List<CreditApplication> all = repository.findAll(spec);
        for (CreditApplication creditApplication : all) {
            List<CreditRequirement> byCompanyId = creditRequirementRepository.findByCompanyId(creditApplication.getCompany().getId());
            if(!byCompanyId.isEmpty()) {
                creditApplication.setRequirement(byCompanyId);
            }
        }

        return all;
    }

    public CreditApplication getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CreditApplication not found with ID " , id));
    }
}
