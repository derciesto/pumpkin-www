package com.ciesto.repository.creditRequirement;

import com.ciesto.model.creditRequirement.RequirementCreditContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RequirementCreditContextRepository extends JpaRepository<RequirementCreditContext, Long> {
    Optional<RequirementCreditContext> findByCreditRequirement_Id(Long creditRequirementId);

}
