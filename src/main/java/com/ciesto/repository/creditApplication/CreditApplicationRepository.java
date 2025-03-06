package com.ciesto.repository.creditApplication;

import com.ciesto.model.creditApplication.CreditApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditApplicationRepository
        extends JpaRepository<CreditApplication, Long>, JpaSpecificationExecutor<CreditApplication> {
}
