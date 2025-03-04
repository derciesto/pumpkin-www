package com.ciesto.service;

import com.ciesto.model.RegisteredPaymentMethod;
import com.ciesto.model.SanctionedLoan;
import com.ciesto.repository.RegisteredPaymentMethodRepository;
import com.ciesto.repository.SanctionedLoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegisteredPaymentMethodService {
    private final RegisteredPaymentMethodRepository repository;
    private final SanctionedLoanRepository sanctionedLoanRepository;

    public RegisteredPaymentMethod createPaymentMethod(RegisteredPaymentMethod paymentMethod, Long sanctionedLoanId) {
        SanctionedLoan loan = sanctionedLoanRepository.findById(sanctionedLoanId)
                .orElseThrow(() -> new RuntimeException("Sanctioned Loan not found"));
        paymentMethod.setSanctionedLoan(loan);
        return repository.save(paymentMethod);
    }

    public List<RegisteredPaymentMethod> getBySanctionedLoan(Long sanctionedLoanId) {
        return repository.findBySanctionedLoan_LoanId(sanctionedLoanId);
    }
}

