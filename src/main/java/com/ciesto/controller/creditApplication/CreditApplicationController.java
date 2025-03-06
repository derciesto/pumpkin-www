package com.ciesto.controller.creditApplication;

import com.ciesto.dto.ApiResponse;
import com.ciesto.model.creditApplication.CreditApplication;
import com.ciesto.service.creditApplication.CreditApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credit-applications")
public class CreditApplicationController {

    private final CreditApplicationService service;

    public CreditApplicationController(CreditApplicationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CreditApplication>> create(
            @RequestParam Long companyId,
            @RequestBody CreditApplication creditApplication) {
        try {
            CreditApplication savedCreditApplication = service.createCreditApplication(companyId, creditApplication);
            return ResponseEntity.ok(ApiResponse.success(savedCreditApplication));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error creating credit application: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CreditApplication>> getById(@PathVariable Long id) {
        try {
            CreditApplication creditApplication = service.getById(id);
            return ResponseEntity.ok(ApiResponse.success(creditApplication));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error fetching credit application: " + e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CreditApplication>>> getAllFiltered(
            @RequestParam(required = false) String companyName,
            @RequestParam(required = false) String purpose,
            @RequestParam(required = false) String identifiedOn,
            @RequestParam(required = false) String sourceChannel,
            @RequestParam(required = false) String status
    ) {
        try {
            List<CreditApplication> applications = service.getAllFiltered(companyName, purpose, identifiedOn, sourceChannel, status);
            return ResponseEntity.ok(ApiResponse.success(applications));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error fetching credit applications: " + e.getMessage()));
        }
    }
}
