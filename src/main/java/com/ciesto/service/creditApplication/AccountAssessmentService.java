package com.ciesto.service.creditApplication;

import com.ciesto.common.customException.ResourceNotFoundException;
import com.ciesto.model.creditApplication.AccountAssessment;
import com.ciesto.model.creditApplication.CreditApplication;
import com.ciesto.repository.creditApplication.AccountAssessmentRepository;
import com.ciesto.repository.creditApplication.CreditApplicationRepository;
import com.ciesto.service.creditApplication.utility.AccountAssessmentSpecification;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class AccountAssessmentService {
    private final AccountAssessmentRepository repository;

    private final CreditApplicationRepository creditApplicationRepository;

    public AccountAssessmentService(AccountAssessmentRepository repository,
                                    CreditApplicationRepository creditApplicationRepository) {
        this.repository = repository;
        this.creditApplicationRepository=creditApplicationRepository;
    }

    @Transactional
    public AccountAssessment save(Long creditApplicationId, AccountAssessment entity) {
        CreditApplication creditApplication = creditApplicationRepository.findById(creditApplicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Credit Application not found", 404L));
        entity.setCreditApplication(creditApplication);
        return repository.save(entity);
    }

    public AccountAssessment getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AccountAssessment not found with id: " + id, 404L));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("AccountAssessment not found with id: " + id, 404L);
        }
        repository.deleteById(id);
    }

    public Page<AccountAssessment> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<AccountAssessment> getFilteredResults(String financialYear, Integer taxablePaid, String source, Pageable pageable) {
        Specification<AccountAssessment> spec = AccountAssessmentSpecification.filterBy(financialYear, taxablePaid, source);
        return repository.findAll(spec, pageable);
    }
}

