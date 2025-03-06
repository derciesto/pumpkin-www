package com.ciesto.repository.creditRequirement;

import com.ciesto.model.creditRequirement.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, Long>, JpaSpecificationExecutor<Proposal> {

    Optional<List<Proposal>> findByCreditRequirementId(Long creditRequirementId);
}