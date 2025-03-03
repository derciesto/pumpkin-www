package com.ciesto.repository;

import com.ciesto.model.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface ProposalRepository extends JpaRepository<Proposal, Long>, JpaSpecificationExecutor<Proposal> {

    Optional<List<Proposal>> findByCreditRequirementId(Long creditRequirementId);
}