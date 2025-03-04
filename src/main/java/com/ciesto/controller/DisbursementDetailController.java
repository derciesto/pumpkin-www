package com.ciesto.controller;

import com.ciesto.model.DisbursementDetail;
import com.ciesto.service.DisbursementDetailService;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/disbursement-details")
@RequiredArgsConstructor
public class DisbursementDetailController {

    private final DisbursementDetailService disbursementDetailService;

    @PostMapping
    public ResponseEntity<DisbursementDetail> createDisbursement(@RequestParam Long loanId, @RequestBody DisbursementDetail detail) {
        return ResponseEntity.ok(disbursementDetailService.createDisbursementDetail(loanId, detail));
    }

    @GetMapping
    public ResponseEntity<Page<DisbursementDetail>> getDisbursements(
            @RequestParam(required = false) LocalDate requestedDate,
            @RequestParam(required = false) LocalDate dueDate,
            @RequestParam(required = false) String requestedBy,
            @RequestParam(required = false) String designation,
            @RequestParam(required = false) String document,
            @RequestParam(required = false) String status,
            Pageable pageable) {

        Specification<DisbursementDetail> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (requestedDate != null) predicates.add(cb.equal(root.get("requestedDate"), requestedDate));
            if (dueDate != null) predicates.add(cb.equal(root.get("dueDate"), dueDate));
            if (requestedBy != null) predicates.add(cb.like(root.get("requestedBy"), "%" + requestedBy + "%"));
            if (designation != null) predicates.add(cb.equal(root.get("designation"), designation));
            if (document != null) predicates.add(cb.like(root.get("document"), "%" + document + "%"));
            if (status != null) predicates.add(cb.equal(root.get("status"), status));
            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return ResponseEntity.ok(disbursementDetailService.getFilteredDisbursements(spec, pageable));
    }

    @GetMapping("/by-loan/{loanId}")
    public ResponseEntity<List<DisbursementDetail>> getDisbursementDetailsByLoanId(@PathVariable Long loanId) {
        List<DisbursementDetail> details = disbursementDetailService.getDisbursementDetailsByLoanId(loanId);
        return ResponseEntity.ok(details);
    }
}

