package com.ciesto.controller;

import com.ciesto.model.ExistingLoan;
import com.ciesto.service.ExistingLoanService;
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
    public List<ExistingLoan> filterLoans(
            @RequestParam(required = false) String lendingInstitute,
            @RequestParam(required = false) String loanType,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(required = false) String source) {

        return loanService.filterExistingLoans(lendingInstitute, loanType, startDate, endDate, source);
    }

    @PostMapping("/add/{companyId}")
    public ResponseEntity<ExistingLoan> addExistingLoan(@PathVariable Long companyId,
                                                        @RequestBody ExistingLoan existingLoan) {
        ExistingLoan savedLoan = loanService.addExistingLoan(companyId, existingLoan);
        return ResponseEntity.ok(savedLoan);
    }
}
