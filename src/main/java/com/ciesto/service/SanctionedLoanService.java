package com.ciesto.service;

import com.ciesto.common.customException.ResourceNotFoundException;
import com.ciesto.model.CreditApplication;
import com.ciesto.model.SanctionedLoan;
import com.ciesto.repository.CreditApplicationRepository;
import com.ciesto.repository.SanctionedLoanRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SanctionedLoanService {

    private final SanctionedLoanRepository sanctionedLoanRepository;
    private final CreditApplicationRepository creditApplicationRepository;

    @Transactional
    public SanctionedLoan createSanctionedLoan(Long creditApplicationId, SanctionedLoan loan) {
        CreditApplication creditApplication = creditApplicationRepository.findById(creditApplicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Credit Application not found", 404L));
        loan.setCreditApplication(creditApplication);
        return sanctionedLoanRepository.save(loan);
    }

    public Page<SanctionedLoan> getFilteredLoans(Specification<SanctionedLoan> spec, Pageable pageable) {
        return sanctionedLoanRepository.findAll(spec, pageable);
    }

    public Optional<SanctionedLoan> getById(Long id) {
        return sanctionedLoanRepository.findById(id);
    }
}

