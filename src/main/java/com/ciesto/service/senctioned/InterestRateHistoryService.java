package com.ciesto.service.senctioned;

import com.ciesto.model.senctioned.InterestRateHistory;
import com.ciesto.model.senctioned.SanctionedLoan;
import com.ciesto.repository.senctioned.InterestRateHistoryRepository;
import com.ciesto.repository.senctioned.SanctionedLoanRepository;
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

