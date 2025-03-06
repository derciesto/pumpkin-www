package com.ciesto.controller.senctioned;

import com.ciesto.dto.ApiResponse;
import com.ciesto.model.senctioned.InterestRateHistory;
import com.ciesto.service.senctioned.InterestRateHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interest-rate-history")
@RequiredArgsConstructor
public class InterestRateHistoryController {

    private final InterestRateHistoryService service;

    @PostMapping
    public ResponseEntity<ApiResponse<InterestRateHistory>> createInterestRateHistory(
            @RequestBody InterestRateHistory history,
            @RequestParam Long sanctionedLoanId) {
        try {
            InterestRateHistory createdHistory = service.createInterestRateHistory(history, sanctionedLoanId);
            return ResponseEntity.ok(ApiResponse.success(createdHistory));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error creating interest rate history: " + e.getMessage()));
        }
    }

    @GetMapping("/by-loan/{sanctionedLoanId}")
    public ResponseEntity<ApiResponse<List<InterestRateHistory>>> getBySanctionedLoan(@PathVariable Long sanctionedLoanId) {
        try {
            List<InterestRateHistory> historyList = service.getBySanctionedLoan(sanctionedLoanId);
            return ResponseEntity.ok(ApiResponse.success(historyList));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error fetching interest rate history: " + e.getMessage()));
        }
    }
}
