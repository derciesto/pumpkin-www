package com.ciesto.service.creditApplication;

import com.ciesto.common.customException.ResourceNotFoundException;
import com.ciesto.model.creditApplication.AccountAssessment;
import com.ciesto.model.creditApplication.CreditApplication;
import com.ciesto.repository.creditApplication.AccountAssessmentRepository;
import com.ciesto.repository.creditApplication.CreditApplicationRepository;
import com.ciesto.service.creditApplication.utility.AccountAssessmentSpecification;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public Page<AccountAssessment> getAll(
            String financialYear, Integer reportedRevenue, Integer grossIncome,
            Integer netIncome, Integer taxableIncome, Integer taxablePaid,
            String source, Pageable pageable) {

        Specification<AccountAssessment> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (financialYear != null) {
                predicates.add(cb.equal(root.get("financialYear"), financialYear));
            }
            if (reportedRevenue != null) {
                predicates.add(cb.equal(root.get("reportedRevenue"), reportedRevenue));
            }
            if (grossIncome != null) {
                predicates.add(cb.equal(root.get("grossIncome"), grossIncome));
            }
            if (netIncome != null) {
                predicates.add(cb.equal(root.get("netIncome"), netIncome));
            }
            if (taxableIncome != null) {
                predicates.add(cb.equal(root.get("taxableIncome"), taxableIncome));
            }
            if (taxablePaid != null) {
                predicates.add(cb.equal(root.get("taxablePaid"), taxablePaid));
            }
            if (source != null) {
                predicates.add(cb.like(cb.lower(root.get("source")), "%" + source.toLowerCase() + "%"));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return repository.findAll(spec, pageable);
    }
    public Page<AccountAssessment> getFilteredResults(String financialYear, Integer taxablePaid, String source, Pageable pageable) {
        Specification<AccountAssessment> spec = AccountAssessmentSpecification.filterBy(financialYear, taxablePaid, source);
        return repository.findAll(spec, pageable);
    }
}

