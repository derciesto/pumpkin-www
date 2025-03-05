package com.ciesto.controller;

import com.ciesto.model.ApplicantDetails;
import com.ciesto.model.CreditRequirement;
import com.ciesto.model.RequirementApplicantDetails;
import com.ciesto.service.CreditRequirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/credit-requirements")
public class CreditRequirementController {

    @Autowired
    private CreditRequirementService service;

    @GetMapping
    public List<CreditRequirement> getAllCreditRequirements() {
        return service.getAllCreditRequirements();
    }

    @GetMapping("/filter")
    public List<CreditRequirement> filterCreditRequirements(
            @RequestParam(required = false) String companyName,
            @RequestParam(required = false) String purpose,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String identifiedOn,
            @RequestParam(required = false) String sourceChannel) {

        return service.filterCreditRequirements(companyName, purpose, status, identifiedOn, sourceChannel);
    }

    @PostMapping
    public CreditRequirement createCreditRequirement(@RequestBody CreditRequirement creditRequirement) {
        return service.saveCreditRequirement(creditRequirement);
    }

    @DeleteMapping("/{id}")
    public void deleteCreditRequirement(@PathVariable Long id) {
        service.deleteCreditRequirement(id);
    }

    @PostMapping("/add")
    public ResponseEntity<CreditRequirement> addCreditRequirement(@RequestBody CreditRequirement creditRequirement) {
        return ResponseEntity.ok(service.addOrUpdateCreditRequirement(creditRequirement));
    }

    @PostMapping("/applicant")
    public ResponseEntity<RequirementApplicantDetails> createApplicant(
            @RequestParam Long creditRequirementId,
            @RequestParam(required = false) MultipartFile panCard,
            @RequestParam(required = false) MultipartFile aadharCard,
            @RequestParam(required = false) MultipartFile msmeRegistration,
            @RequestPart RequirementApplicantDetails applicant) {
        return ResponseEntity.ok(service.addRequirementApplicant(creditRequirementId, applicant, panCard, aadharCard, msmeRegistration));
    }

    @GetMapping("/applicant/{id}")
    public ResponseEntity<RequirementApplicantDetails> getApplicantById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getApplicantById(id));
    }

    @GetMapping("/applicant/requirement/{creditRequirementId}")
    public ResponseEntity<List<RequirementApplicantDetails>> getApplicantsByCreditRequirement(@PathVariable Long creditRequirementId) {
        return ResponseEntity.ok(service.getApplicantsByCreditRequirement(creditRequirementId));
    }

    @PutMapping("/applicant/{id}")
    public ResponseEntity<RequirementApplicantDetails> updateApplicant(
            @PathVariable Long id, @RequestBody RequirementApplicantDetails updatedApplicant) {
        return ResponseEntity.ok(service.updateApplicant(id, updatedApplicant));
    }

    @DeleteMapping("/applicant/{id}")
    public ResponseEntity<Void> deleteApplicant(@PathVariable Long id) {
        service.deleteApplicant(id);
        return ResponseEntity.noContent().build();
    }
}
