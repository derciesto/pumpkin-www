package com.ciesto.repository.creditApplication;

import com.ciesto.model.creditApplication.AccountAssessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountAssessmentRepository extends JpaRepository<AccountAssessment, Long>, JpaSpecificationExecutor<AccountAssessment> {
}
