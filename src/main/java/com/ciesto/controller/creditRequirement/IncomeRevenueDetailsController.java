package com.ciesto.controller.creditRequirement;

import com.ciesto.dto.ApiResponse;
import com.ciesto.model.senctioned.IncomeRevenueDetails;
import com.ciesto.repository.creditRequirement.IncomeRevenueDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/income-revenue-details")
@RequiredArgsConstructor
public class IncomeRevenueDetailsController {
    private final IncomeRevenueDetailsRepository repository;

    @GetMapping
    public ResponseEntity<ApiResponse<List<IncomeRevenueDetails>>> getAll() {
        List<IncomeRevenueDetails> details = repository.findAll();
        return ResponseEntity.ok(ApiResponse.success(details));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<IncomeRevenueDetails>> getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(detail -> ResponseEntity.ok(ApiResponse.success(detail)))
                .orElseGet(() -> ResponseEntity.badRequest().body(ApiResponse.error("Income revenue detail not found")));
    }

    @GetMapping("/credit-context/{creditContextId}")
    public ResponseEntity<ApiResponse<List<IncomeRevenueDetails>>> getByCreditContext(@PathVariable Long creditContextId) {
        List<IncomeRevenueDetails> details = repository.findByCreditContextId(creditContextId);
        return ResponseEntity.ok(ApiResponse.success(details));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<IncomeRevenueDetails>> create(@RequestBody IncomeRevenueDetails details) {
        IncomeRevenueDetails savedDetails = repository.save(details);
        return ResponseEntity.ok(ApiResponse.success(savedDetails));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<IncomeRevenueDetails>> update(@PathVariable Long id, @RequestBody IncomeRevenueDetails details) {
        if (!repository.existsById(id)) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Income revenue detail not found"));
        }
        details.setId(id);
        IncomeRevenueDetails updatedDetails = repository.save(details);
        return ResponseEntity.ok(ApiResponse.success(updatedDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Income revenue detail not found"));
        }
        repository.deleteById(id);
        return ResponseEntity.ok(ApiResponse.success("Income revenue detail deleted successfully"));
    }
}

