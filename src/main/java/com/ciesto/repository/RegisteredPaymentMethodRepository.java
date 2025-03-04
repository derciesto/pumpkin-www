package com.ciesto.repository;

import com.ciesto.model.RegisteredPaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RegisteredPaymentMethodRepository extends JpaRepository<RegisteredPaymentMethod, Long> {
    List<RegisteredPaymentMethod> findBySanctionedLoan_LoanId(Long sanctionedLoanId);
}

