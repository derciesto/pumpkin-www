package com.ciesto.repository;

import com.ciesto.model.AccountAssessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountAssessmentRepository extends JpaRepository<AccountAssessment, Long>, JpaSpecificationExecutor<AccountAssessment> {
}
