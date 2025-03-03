package com.ciesto.controller.creditApplication;

import com.ciesto.model.Proposal;
import com.ciesto.service.ProposalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/proposals")
public class ProposalController {

    private static final Logger logger = LoggerFactory.getLogger(ProposalController.class);
    private final ProposalService proposalService;

    public ProposalController(ProposalService proposalService) {
        this.proposalService = proposalService;
    }

    @GetMapping("/filter")
    public List<Proposal> getFilteredProposals(
            @RequestParam(required = false) String lendingInstitution,
            @RequestParam(required = false) Integer loanDuration,
            @RequestParam(required = false) Long loanAmount,
            @RequestParam(required = false) Double interestRate,
            @RequestParam(required = false) String sourceChannel,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate proposalDate,
            @RequestParam(required = false) String status) {

        logger.info("Received filters: lendingInstitution={}, loanDuration={}, loanAmount={}, interestRate={}, sourceChannel={}, proposalDate={}, status={}",
                lendingInstitution, loanDuration, loanAmount, interestRate, sourceChannel, proposalDate, status);

        List<Proposal> proposals = proposalService.getFilteredProposals(lendingInstitution, loanDuration, loanAmount, interestRate, sourceChannel, proposalDate, status);

        logger.info("Returning {} proposals", proposals.size());
        return proposals;
    }

    @PostMapping("/{creditRequirementId}")
    public ResponseEntity<Proposal> addProposal(@PathVariable Long creditRequirementId, @RequestBody Proposal proposal) {
        Proposal savedProposal = proposalService.saveProposal(creditRequirementId, proposal);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProposal);
    }

    @GetMapping("/creditRequirement/{creditRequirementId}")
    public ResponseEntity<List<Proposal>> getProposalsByCreditRequirement(@PathVariable Long creditRequirementId) {
        List<Proposal> proposals = proposalService.getProposalsByCreditRequirement(creditRequirementId);
        return ResponseEntity.ok(proposals);
    }

    @GetMapping("/fetch/{id}")
    public ResponseEntity<Proposal> getProposalById(@PathVariable Long id) {
        Proposal proposal = proposalService.getProposal(id);
        return ResponseEntity.ok(proposal);
    }
}
