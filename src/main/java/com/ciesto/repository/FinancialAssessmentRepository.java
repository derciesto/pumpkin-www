package com.ciesto.repository;

import com.ciesto.model.FinancialAssessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FinancialAssessmentRepository
        extends JpaRepository<FinancialAssessment, Long>, JpaSpecificationExecutor<FinancialAssessment> {
}
