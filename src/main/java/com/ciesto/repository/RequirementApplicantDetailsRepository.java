package com.ciesto.repository;

import com.ciesto.model.RequirementApplicantDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RequirementApplicantDetailsRepository extends JpaRepository<RequirementApplicantDetails, Long> {
    List<RequirementApplicantDetails> findByCreditRequirement_Id(Long creditRequirementId);
}

