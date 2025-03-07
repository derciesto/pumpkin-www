package com.ciesto.repository.creditRequirement;

import com.ciesto.model.creditRequirement.RequirementCreditContext;
import com.ciesto.model.senctioned.IncomeRevenueDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeRevenueDetailsRepository extends JpaRepository<IncomeRevenueDetails, Long> {
    List<IncomeRevenueDetails> findByCreditContextId(Long creditContextId);
}