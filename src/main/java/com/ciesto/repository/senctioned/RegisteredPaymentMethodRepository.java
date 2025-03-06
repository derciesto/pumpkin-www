package com.ciesto.repository.senctioned;

import com.ciesto.model.senctioned.RegisteredPaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RegisteredPaymentMethodRepository extends JpaRepository<RegisteredPaymentMethod, Long> {
    List<RegisteredPaymentMethod> findBySanctionedLoan_LoanId(Long sanctionedLoanId);
}

