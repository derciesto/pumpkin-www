package com.ciesto.controller;

import com.ciesto.model.SanctionedLoan;
import com.ciesto.service.SanctionedLoanService;
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
    public ResponseEntity<SanctionedLoan> createLoan(@RequestParam Long creditApplicationId, @RequestBody SanctionedLoan loan) {
        return ResponseEntity.ok(sanctionedLoanService.createSanctionedLoan(creditApplicationId, loan));
    }

    @GetMapping
    public ResponseEntity<Page<SanctionedLoan>> getLoans(
            @RequestParam(required = false) Long sanctionedAmount,
            @RequestParam(required = false) String product,
            @RequestParam(required = false) Integer tenure,
            @RequestParam(required = false) String status,
            Pageable pageable) {

        Specification<SanctionedLoan> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (sanctionedAmount != null) predicates.add(cb.equal(root.get("sanctionedAmount"), sanctionedAmount));
            if (product != null) predicates.add(cb.like(root.get("product"), "%" + product + "%"));
            if (tenure != null) predicates.add(cb.equal(root.get("tenure"), tenure));
            if (status != null) predicates.add(cb.equal(root.get("status"), status));
            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return ResponseEntity.ok(sanctionedLoanService.getFilteredLoans(spec, pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<SanctionedLoan> loans = sanctionedLoanService.getById(id);
        if(loans.isPresent()) {
            return ResponseEntity.ok(loans.get());
        } else {
            return ResponseEntity.accepted().body("Can not find loan with mentioned Id :" + id );
        }
    }
}

