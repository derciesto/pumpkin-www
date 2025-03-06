package com.ciesto.repository.creditRequirement;

import com.ciesto.model.creditRequirement.RequirementApplicantDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RequirementApplicantDetailsRepository extends JpaRepository<RequirementApplicantDetails, Long> {
    List<RequirementApplicantDetails> findByCreditRequirement_Id(Long creditRequirementId);
}

