package com.ciesto.repository.creditApplication;

import com.ciesto.model.creditApplication.FinancialAssessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialAssessmentRepository
        extends JpaRepository<FinancialAssessment, Long>, JpaSpecificationExecutor<FinancialAssessment> {
}
