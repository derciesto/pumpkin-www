package com.ciesto.repository;

import com.ciesto.model.ExistingLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ExistingLoanRepository extends JpaRepository<ExistingLoan, Long>, JpaSpecificationExecutor<ExistingLoan> {
}
