package com.ciesto.service;

import com.ciesto.model.InterestRateHistory;
import com.ciesto.model.SanctionedLoan;
import com.ciesto.repository.InterestRateHistoryRepository;
import com.ciesto.repository.SanctionedLoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InterestRateHistoryService {
    private final InterestRateHistoryRepository repository;
    private final SanctionedLoanRepository sanctionedLoanRepository;

    public InterestRateHistory createInterestRateHistory(InterestRateHistory history, Long sanctionedLoanId) {
        SanctionedLoan loan = sanctionedLoanRepository.findById(sanctionedLoanId)
                .orElseThrow(() -> new RuntimeException("Sanctioned Loan not found"));
        history.setSanctionedLoan(loan);
        return repository.save(history);
    }

    public List<InterestRateHistory> getBySanctionedLoan(Long sanctionedLoanId) {
        return repository.findBySanctionedLoan_LoanId(sanctionedLoanId);
    }
}

