package com.ciesto.controller.creditRequirement;

import com.ciesto.dto.ApiResponse;
import com.ciesto.model.creditRequirement.ExistingLoan;
import com.ciesto.service.creditRequest.ExistingLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class ExistingLoanController {

    @Autowired
    private ExistingLoanService loanService;

    @GetMapping("/filter")
    public ResponseEntity<ApiResponse<List<ExistingLoan>>> filterLoans(
            @RequestParam(required = false) String lendingInstitute,
            @RequestParam(required = false) String loanType,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(required = false) String source) {
        try {
            List<ExistingLoan> loans = loanService.filterExistingLoans(lendingInstitute, loanType, startDate, endDate, source);
            return ResponseEntity.ok(ApiResponse.success(loans));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error fetching loans: " + e.getMessage()));
        }
    }

    @PostMapping("/add/{companyId}")
    public ResponseEntity<ApiResponse<ExistingLoan>> addExistingLoan(@PathVariable Long companyId,
                                                                     @RequestBody ExistingLoan existingLoan) {
        try {
            ExistingLoan savedLoan = loanService.addExistingLoan(companyId, existingLoan);
            return ResponseEntity.ok(ApiResponse.success(savedLoan));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error adding loan: " + e.getMessage()));
        }
    }
}
