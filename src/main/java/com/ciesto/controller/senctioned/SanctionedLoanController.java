package com.ciesto.controller.senctioned;

import com.ciesto.dto.ApiResponse;
import com.ciesto.model.senctioned.SanctionedLoan;
import com.ciesto.service.senctioned.SanctionedLoanService;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sanctioned-loans")
@RequiredArgsConstructor
public class SanctionedLoanController {

    private final SanctionedLoanService sanctionedLoanService;

    @PostMapping
    public ResponseEntity<ApiResponse<SanctionedLoan>> createLoan(
            @RequestParam Long creditApplicationId,
            @RequestBody SanctionedLoan loan) {
        try {
            SanctionedLoan createdLoan = sanctionedLoanService.createSanctionedLoan(creditApplicationId, loan);
            return ResponseEntity.ok(ApiResponse.success(createdLoan));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error creating loan: " + e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<SanctionedLoan>>> getLoans(
            @RequestParam(required = false) Long sanctionedAmount,
            @RequestParam(required = false) String product,
            @RequestParam(required = false) Integer tenure,
            @RequestParam(required = false) String status,
            Pageable pageable) {

        try {
            Specification<SanctionedLoan> spec = (root, query, cb) -> {
                List<Predicate> predicates = new ArrayList<>();
                if (sanctionedAmount != null) predicates.add(cb.equal(root.get("sanctionedAmount"), sanctionedAmount));
                if (product != null) predicates.add(cb.like(root.get("product"), "%" + product + "%"));
                if (tenure != null) predicates.add(cb.equal(root.get("tenure"), tenure));
                if (status != null) predicates.add(cb.equal(root.get("status"), status));
                return cb.and(predicates.toArray(new Predicate[0]));
            };

            Page<SanctionedLoan> loans = sanctionedLoanService.getFilteredLoans(spec, pageable);
            return ResponseEntity.ok(ApiResponse.success(loans));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error fetching loans: " + e.getMessage()));
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<SanctionedLoan>> getById(@PathVariable Long id) {
        try {
            Optional<SanctionedLoan> loan = sanctionedLoanService.getById(id);
            if (loan.isPresent()) {
                return ResponseEntity.ok(ApiResponse.success(loan.get()));
            } else {
                return ResponseEntity.accepted().body(ApiResponse.error("Cannot find loan with the mentioned ID: " + id));
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error fetching loan details: " + e.getMessage()));
        }
    }
}
