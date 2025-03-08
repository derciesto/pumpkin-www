package com.ciesto.controller.creditApplication;

import com.ciesto.dto.ApiResponse;
import com.ciesto.dto.wrapper.creditApplication.LoanApplicationRequestDTO;
import com.ciesto.model.creditApplication.CreditApplication;
import com.ciesto.service.creditApplication.CreditApplicationService;
import com.ciesto.service.creditApplication.LoanApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credit-applications")
public class CreditApplicationController {

    private final CreditApplicationService service;

    private final LoanApplicationService loanApplicationService;

    public CreditApplicationController(CreditApplicationService service,
                                       LoanApplicationService loanApplicationService) {
        this.service = service;
        this.loanApplicationService = loanApplicationService;
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

    @PostMapping("/apply")
    public ResponseEntity<ApiResponse<Long>> submitApplication(@RequestBody LoanApplicationRequestDTO request) {
        try {
            CreditApplication application = loanApplicationService.createApplication(request);
            return ResponseEntity.ok(ApiResponse.success(application.getId()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error submitting credit application: " + e.getMessage()));
        }
    }
}
