package com.ciesto.repository;

import com.ciesto.model.CreditApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CreditApplicationRepository
        extends JpaRepository<CreditApplication, Long>, JpaSpecificationExecutor<CreditApplication> {
}
