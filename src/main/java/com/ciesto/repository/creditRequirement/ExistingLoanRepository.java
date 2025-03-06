package com.ciesto.repository.creditRequirement;

import com.ciesto.model.creditRequirement.ExistingLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ExistingLoanRepository extends JpaRepository<ExistingLoan, Long>, JpaSpecificationExecutor<ExistingLoan> {
}
