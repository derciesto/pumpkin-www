package com.ciesto.repository;

import com.ciesto.model.RequirementApplicantDetails;
import com.ciesto.model.RequirementCreditContext;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RequirementCreditContextRepository extends JpaRepository<RequirementCreditContext, Long> {
    Optional<RequirementCreditContext> findByCreditRequirement_Id(Long creditRequirementId);

}
