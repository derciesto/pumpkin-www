package com.ciesto.controller.creditApplication;

import com.ciesto.dto.ApiResponse;
import com.ciesto.model.creditApplication.FinancialAssessment;
import com.ciesto.service.creditApplication.FinancialAssessmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/financial-assessments")
public class FinancialAssessmentController {

    private final FinancialAssessmentService financialAssessmentService;

    public FinancialAssessmentController(FinancialAssessmentService financialAssessmentService) {
        this.financialAssessmentService = financialAssessmentService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<FinancialAssessment>> createFinancialAssessment(
            @RequestParam Long creditApplicationId,
            @RequestBody FinancialAssessment financialAssessment) {
        try {
            FinancialAssessment saved = financialAssessmentService.createFinancialAssessment(creditApplicationId, financialAssessment);
            return ResponseEntity.ok(ApiResponse.success(saved));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error creating financial assessment: " + e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<FinancialAssessment>>> getAllFinancialAssessments(
            @RequestParam(required = false) String financialYear,
            @RequestParam(required = false) String month,
            @RequestParam(required = false) Integer reportedSales,
            @RequestParam(required = false) Integer reportedPurchases,
            @RequestParam(required = false) String source,
            @RequestParam(required = false) Integer taxesPaid) {
        try {
            List<FinancialAssessment> results = financialAssessmentService.getAllFinancialAssessments(
                    financialYear, month, reportedSales, reportedPurchases, source, taxesPaid);
            return ResponseEntity.ok(ApiResponse.success(results));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error fetching financial assessments: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<FinancialAssessment>> getFinancialAssessmentById(@PathVariable Long id) {
        try {
            FinancialAssessment result = financialAssessmentService.getFinancialAssessmentById(id);
            return ResponseEntity.ok(ApiResponse.success(result));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error fetching financial assessment: " + e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<FinancialAssessment>> updateFinancialAssessment(
            @PathVariable Long id,
            @RequestBody FinancialAssessment financialAssessment) {
        try {
            FinancialAssessment updated = financialAssessmentService.updateFinancialAssessment(id, financialAssessment);
            return ResponseEntity.ok(ApiResponse.success(updated));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error updating financial assessment: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteFinancialAssessment(@PathVariable Long id) {
        try {
            financialAssessmentService.deleteFinancialAssessment(id);
            return ResponseEntity.noContent().build(); // Void response doesn't need to be wrapped in ApiResponse
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error deleting financial assessment: " + e.getMessage()));
        }
    }
}
