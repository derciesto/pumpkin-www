package com.ciesto.repository;

import com.ciesto.model.InterestRateHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface InterestRateHistoryRepository extends JpaRepository<InterestRateHistory, Long> {
    List<InterestRateHistory> findBySanctionedLoan_LoanId(Long sanctionedLoanId);
}
