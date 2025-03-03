package com.ciesto.controller.creditApplication;

import com.ciesto.model.ApplicantDetails;
import com.ciesto.service.creditApplicatoin.ApplicantDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applicants")
public class ApplicantDetailsController {

    @Autowired
    private ApplicantDetailsService service;

    // Create a new applicant
    @PostMapping
    public ResponseEntity<ApplicantDetails> createApplicant(
            @RequestParam Long creditApplicationId,
            @Validated @RequestBody ApplicantDetails applicant) {
        return ResponseEntity.ok(service.createApplicant(creditApplicationId, applicant));
    }

    // Get applicant by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApplicantDetails> getApplicantById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getApplicantById(id));
    }

    // Get all applicants with filtering
    @GetMapping
    public ResponseEntity<List<ApplicantDetails>> getAllApplicants(
            @RequestParam(required = false) String pan,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String employmentType,
            @RequestParam(required = false) String state) {
        return ResponseEntity.ok(service.getApplicantsWithFilters(pan, phone, employmentType, state));
    }

    // Update an applicant
    @PutMapping("/{id}")
    public ResponseEntity<ApplicantDetails> updateApplicant(
            @PathVariable Long id,
            @Validated @RequestBody ApplicantDetails applicantDetails) {
        return ResponseEntity.ok(service.updateApplicant(id, applicantDetails));
    }

    // Delete an applicant
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteApplicant(@PathVariable Long id) {
        service.deleteApplicant(id);
        return ResponseEntity.ok("Applicant deleted successfully.");
    }
}
