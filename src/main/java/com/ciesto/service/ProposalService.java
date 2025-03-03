package com.ciesto.service;

import com.ciesto.common.Constants;
import com.ciesto.common.customException.CreditRequirementNotFound;
import com.ciesto.common.customException.ProposalNotAvailable;
import com.ciesto.model.CreditRequirement;
import com.ciesto.model.Proposal;
import com.ciesto.repository.CreditRequirementRepository;
import com.ciesto.repository.ProposalRepository;
import com.ciesto.service.utility.ProposalSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class ProposalService {

    @Autowired
    private CreditRequirementRepository creditRequirementRepository;

    private final ProposalRepository proposalRepository;

    public ProposalService(ProposalRepository proposalRepository) {
        this.proposalRepository = proposalRepository;
    }

    public List<Proposal> getFilteredProposals(String lendingInstitution, Integer loanDuration, Long loanAmount,
                                               Double interestRate, String sourceChannel, LocalDate proposalDate, String status) {
        Specification<Proposal> spec = ProposalSpecification.filterProposals(
                lendingInstitution, loanDuration, loanAmount, interestRate, sourceChannel, proposalDate, status
        );
        return proposalRepository.findAll(spec);
    }

    public Proposal getProposal(Long id) {
        return proposalRepository.findById(id)
                .orElseThrow(() -> new ProposalNotAvailable("Proposal not found with ID: " + id, Constants.PROPOSAL_NOT_AVAILABLE , HttpStatus.NOT_FOUND));
    }

    public List<Proposal> getProposalsByCreditRequirement(Long creditRequirementId) {
        return proposalRepository.findByCreditRequirementId(creditRequirementId)
                .orElseThrow(() -> new CreditRequirementNotFound("Credit requirement not found: "+ creditRequirementId,Constants.CREDIT_REQUIREMENT_NOT_FOUND,HttpStatus.NOT_FOUND));
    }

    public Proposal saveProposal(Long creditRequirementId, Proposal proposal) {
        CreditRequirement creditRequirement = creditRequirementRepository.findById(creditRequirementId)
                .orElseThrow(() -> new CreditRequirementNotFound("Credit requirement not found: "+ creditRequirementId,Constants.CREDIT_REQUIREMENT_NOT_FOUND,HttpStatus.NOT_FOUND));

        proposal.setCreditRequirement(creditRequirement);
        return proposalRepository.save(proposal);
    }
}