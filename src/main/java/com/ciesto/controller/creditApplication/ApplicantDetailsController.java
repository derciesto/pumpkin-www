package com.ciesto.controller.creditApplication;

import com.ciesto.dto.ApiResponse;
import com.ciesto.model.creditApplication.ApplicantDetails;
import com.ciesto.service.creditApplication.ApplicantDetailsService;
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

    @PostMapping
    public ResponseEntity<ApiResponse<ApplicantDetails>> createApplicant(
            @RequestParam Long creditApplicationId,
            @Validated @RequestBody ApplicantDetails applicant) {
        try {
            ApplicantDetails createdApplicant = service.createApplicant(creditApplicationId, applicant);
            return ResponseEntity.ok(ApiResponse.success(createdApplicant));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error creating applicant: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ApplicantDetails>> getApplicantById(@PathVariable Long id) {
        try {
            ApplicantDetails applicant = service.getApplicantById(id);
            return ResponseEntity.ok(ApiResponse.success(applicant));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error fetching applicant: " + e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ApplicantDetails>>> getAllApplicants(
            @RequestParam(required = false) String pan,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String employmentType,
            @RequestParam(required = false) String state) {
        try {
            List<ApplicantDetails> applicants = service.getApplicantsWithFilters(pan, phone, employmentType, state);
            return ResponseEntity.ok(ApiResponse.success(applicants));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error fetching applicants: " + e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ApplicantDetails>> updateApplicant(
            @PathVariable Long id,
            @Validated @RequestBody ApplicantDetails applicantDetails) {
        try {
            ApplicantDetails updatedApplicant = service.updateApplicant(id, applicantDetails);
            return ResponseEntity.ok(ApiResponse.success(updatedApplicant));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error updating applicant: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteApplicant(@PathVariable Long id) {
        try {
            service.deleteApplicant(id);
            return ResponseEntity.ok(ApiResponse.success("Applicant deleted successfully."));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error deleting applicant: " + e.getMessage()));
        }
    }
}
