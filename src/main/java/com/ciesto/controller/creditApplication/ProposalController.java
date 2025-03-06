package com.ciesto.controller.creditApplication;

import com.ciesto.dto.ApiResponse;
import com.ciesto.model.creditRequirement.Proposal;
import com.ciesto.service.creditRequest.ProposalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
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
    public ResponseEntity<ApiResponse<List<Proposal>>> getFilteredProposals(
            @RequestParam(required = false) String lendingInstitution,
            @RequestParam(required = false) Integer loanDuration,
            @RequestParam(required = false) Long loanAmount,
            @RequestParam(required = false) Double interestRate,
            @RequestParam(required = false) String sourceChannel,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate proposalDate,
            @RequestParam(required = false) String status) {

        logger.info("Received filters: lendingInstitution={}, loanDuration={}, loanAmount={}, interestRate={}, sourceChannel={}, proposalDate={}, status={}",
                lendingInstitution, loanDuration, loanAmount, interestRate, sourceChannel, proposalDate, status);

        try {
            List<Proposal> proposals = proposalService.getFilteredProposals(lendingInstitution, loanDuration, loanAmount, interestRate, sourceChannel, proposalDate, status);
            return ResponseEntity.ok(ApiResponse.success(proposals));
        } catch (Exception e) {
            logger.error("Error fetching filtered proposals: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error fetching filtered proposals: " + e.getMessage()));
        }
    }

    @PostMapping("/{creditRequirementId}")
    public ResponseEntity<ApiResponse<Proposal>> addProposal(@PathVariable Long creditRequirementId, @RequestBody Proposal proposal) {
        try {
            Proposal savedProposal = proposalService.saveProposal(creditRequirementId, proposal);
            return ResponseEntity.ok(ApiResponse.success(savedProposal));
        } catch (Exception e) {
            logger.error("Error adding proposal: {}", e.getMessage());
            return ResponseEntity.badRequest().body(ApiResponse.error("Error adding proposal: " + e.getMessage()));
        }
    }

    @GetMapping("/creditRequirement/{creditRequirementId}")
    public ResponseEntity<ApiResponse<List<Proposal>>> getProposalsByCreditRequirement(@PathVariable Long creditRequirementId) {
        try {
            List<Proposal> proposals = proposalService.getProposalsByCreditRequirement(creditRequirementId);
            return ResponseEntity.ok(ApiResponse.success(proposals));
        } catch (Exception e) {
            logger.error("Error fetching proposals by credit requirement: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error fetching proposals: " + e.getMessage()));
        }
    }

    @GetMapping("/fetch/{id}")
    public ResponseEntity<ApiResponse<Proposal>> getProposalById(@PathVariable Long id) {
        try {
            Proposal proposal = proposalService.getProposal(id);
            return ResponseEntity.ok(ApiResponse.success(proposal));
        } catch (Exception e) {
            logger.error("Error fetching proposal by ID: {}", e.getMessage());
            return ResponseEntity.badRequest().body(ApiResponse.error("Error fetching proposal: " + e.getMessage()));
        }
    }
}
