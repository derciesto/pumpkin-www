package com.ciesto.controller.creditApplication;

import com.ciesto.model.FinancialAssessment;
import com.ciesto.service.creditApplicatoin.FinancialAssessmentService;
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
    public ResponseEntity<FinancialAssessment> createFinancialAssessment(@RequestParam Long creditApplicationId,@RequestBody FinancialAssessment financialAssessment) {
        FinancialAssessment saved = financialAssessmentService.createFinancialAssessment(creditApplicationId, financialAssessment);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<FinancialAssessment>> getAllFinancialAssessments(
            @RequestParam(required = false) String financialYear,
            @RequestParam(required = false) String month,
            @RequestParam(required = false) Integer reportedSales,
            @RequestParam(required = false) Integer reportedPurchases,
            @RequestParam(required = false) String source,
            @RequestParam(required = false) Integer taxesPaid) {
        List<FinancialAssessment> results = financialAssessmentService.getAllFinancialAssessments(financialYear, month, reportedSales, reportedPurchases, source, taxesPaid);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FinancialAssessment> getFinancialAssessmentById(@PathVariable Long id) {
        FinancialAssessment result = financialAssessmentService.getFinancialAssessmentById(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FinancialAssessment> updateFinancialAssessment(@PathVariable Long id, @RequestBody FinancialAssessment financialAssessment) {
        FinancialAssessment updated = financialAssessmentService.updateFinancialAssessment(id, financialAssessment);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFinancialAssessment(@PathVariable Long id) {
        financialAssessmentService.deleteFinancialAssessment(id);
        return ResponseEntity.noContent().build();
    }
}
