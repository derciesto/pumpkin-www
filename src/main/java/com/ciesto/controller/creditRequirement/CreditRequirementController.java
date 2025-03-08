package com.ciesto.controller.creditRequirement;

import com.ciesto.dto.ApiResponse;
import com.ciesto.dto.wrapper.creditRequirement.LoanRequest;
import com.ciesto.model.creditRequirement.CreditRequirement;
import com.ciesto.model.creditRequirement.RequirementApplicantDetails;
import com.ciesto.service.creditRequest.CreateCreditRequirementService;
import com.ciesto.service.creditRequest.CreditRequirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/credit-requirements")
public class CreditRequirementController {

    @Autowired
    private CreditRequirementService service;

    @Autowired
    private CreateCreditRequirementService createCreditRequirementService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CreditRequirement>>> getAllCreditRequirements() {
        return ResponseEntity.ok(ApiResponse.success(service.getAllCreditRequirements()));
    }

    @GetMapping("/filter")
    public ResponseEntity<ApiResponse<List<CreditRequirement>>> filterCreditRequirements(
            @RequestParam(required = false) String companyName,
            @RequestParam(required = false) String purpose,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String identifiedOn,
            @RequestParam(required = false) String sourceChannel) {
        return ResponseEntity.ok(ApiResponse.success(service.filterCreditRequirements(companyName, purpose, status, identifiedOn, sourceChannel)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CreditRequirement>> createCreditRequirement(@RequestBody CreditRequirement creditRequirement) {
        try {
            return ResponseEntity.ok(ApiResponse.success(service.saveCreditRequirement(creditRequirement)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error saving credit requirement: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCreditRequirement(@PathVariable Long id) {
        try {
            service.deleteCreditRequirement(id);
            return ResponseEntity.ok(ApiResponse.success(null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error deleting credit requirement: " + e.getMessage()));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<CreditRequirement>> addCreditRequirement(@RequestBody CreditRequirement creditRequirement) {
        try {
            return ResponseEntity.ok(ApiResponse.success(service.addOrUpdateCreditRequirement(creditRequirement)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error adding credit requirement: " + e.getMessage()));
        }
    }

    @PostMapping("/applicant")
    public ResponseEntity<ApiResponse<RequirementApplicantDetails>> createApplicant(
            @RequestParam Long creditRequirementId,
            @RequestParam(required = false) MultipartFile panCard,
            @RequestParam(required = false) MultipartFile aadharCard,
            @RequestParam(required = false) MultipartFile msmeRegistration,
            @RequestPart RequirementApplicantDetails applicant) {
        try {
            return ResponseEntity.ok(ApiResponse.success(service.addRequirementApplicant(creditRequirementId, applicant, panCard, aadharCard, msmeRegistration)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error creating applicant: " + e.getMessage()));
        }
    }

    @GetMapping("/applicant/{id}")
    public ResponseEntity<ApiResponse<RequirementApplicantDetails>> getApplicantById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(service.getApplicantById(id)));
    }

    @GetMapping("/applicant/requirement/{creditRequirementId}")
    public ResponseEntity<ApiResponse<List<RequirementApplicantDetails>>> getApplicantsByCreditRequirement(@PathVariable Long creditRequirementId) {
        return ResponseEntity.ok(ApiResponse.success(service.getApplicantsByCreditRequirement(creditRequirementId)));
    }

    @PutMapping("/applicant/{id}")
    public ResponseEntity<ApiResponse<RequirementApplicantDetails>> updateApplicant(
            @PathVariable Long id, @RequestBody RequirementApplicantDetails updatedApplicant) {
        try {
            return ResponseEntity.ok(ApiResponse.success(service.updateApplicant(id, updatedApplicant)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error updating applicant: " + e.getMessage()));
        }
    }

    @DeleteMapping("/applicant/{id}")
    public ResponseEntity<ApiResponse<Long>> deleteApplicant(@PathVariable Long id) {
        try {
            service.deleteApplicant(id);
            return ResponseEntity.ok(ApiResponse.success(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error deleting applicant: " + e.getMessage()));
        }
    }

    @PostMapping("/apply")
    public ResponseEntity<ApiResponse<String>> applyForLoan(@RequestBody LoanRequest loanRequest) {
        try {
            createCreditRequirementService.createRequirement(loanRequest);
            String message = "Loan application received successfully for amount: " + loanRequest.getLoanAmount();
            return ResponseEntity.ok(ApiResponse.success(message));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error while applying for loan: " + e.getMessage()));
        }
    }

}
