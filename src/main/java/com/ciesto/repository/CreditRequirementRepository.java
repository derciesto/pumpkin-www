package com.ciesto.repository;

import com.ciesto.model.CreditRequirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditRequirementRepository extends JpaRepository<CreditRequirement, Long> {

}
