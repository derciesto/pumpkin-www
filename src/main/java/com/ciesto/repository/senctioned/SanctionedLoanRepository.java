package com.ciesto.repository.senctioned;

import com.ciesto.model.senctioned.SanctionedLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SanctionedLoanRepository extends JpaRepository<SanctionedLoan, Long>, JpaSpecificationExecutor<SanctionedLoan> {
}

