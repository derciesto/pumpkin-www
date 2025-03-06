package com.ciesto.repository.creditApplication;

import com.ciesto.model.creditApplication.ApplicantDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantDetailsRepository
        extends JpaRepository<ApplicantDetails, Long>, JpaSpecificationExecutor<ApplicantDetails> {
}
