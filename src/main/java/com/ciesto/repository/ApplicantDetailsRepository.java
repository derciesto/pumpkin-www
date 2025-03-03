package com.ciesto.repository;

import com.ciesto.model.ApplicantDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ApplicantDetailsRepository
        extends JpaRepository<ApplicantDetails, Long>, JpaSpecificationExecutor<ApplicantDetails> {
}
