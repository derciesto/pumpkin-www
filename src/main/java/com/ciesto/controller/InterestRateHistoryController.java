package com.ciesto.controller;

import com.ciesto.model.InterestRateHistory;
import com.ciesto.service.InterestRateHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interest-rate-history")
@RequiredArgsConstructor
public class InterestRateHistoryController {
    private final InterestRateHistoryService service;

    @PostMapping
    public InterestRateHistory createInterestRateHistory(@RequestBody InterestRateHistory history,
                                                         @RequestParam Long sanctionedLoanId) {
        return service.createInterestRateHistory(history, sanctionedLoanId);
    }

    @GetMapping("/by-loan/{sanctionedLoanId}")
    public List<InterestRateHistory> getBySanctionedLoan(@PathVariable Long sanctionedLoanId) {
        return service.getBySanctionedLoan(sanctionedLoanId);
    }
}

