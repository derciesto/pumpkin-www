package com.ciesto.repository;

import com.ciesto.model.SanctionedLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SanctionedLoanRepository extends JpaRepository<SanctionedLoan, Long>, JpaSpecificationExecutor<SanctionedLoan> {
}

