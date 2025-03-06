package com.ciesto.repository.creditRequirement;

import com.ciesto.model.creditRequirement.CreditRequirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditRequirementRepository extends JpaRepository<CreditRequirement, Long> {

}
