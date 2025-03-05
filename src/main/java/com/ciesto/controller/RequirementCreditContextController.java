package com.ciesto.controller;

import com.ciesto.model.RequirementCreditContext;
import com.ciesto.service.RequirementCreditContextService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/credit-context")
@RequiredArgsConstructor
public class RequirementCreditContextController {
    private final RequirementCreditContextService service;

    @PostMapping
    public ResponseEntity<RequirementCreditContext> createCreditContext(
            @RequestParam Long creditRequirementId,
            @RequestParam(required = false) MultipartFile cancelledCheck,
            @RequestParam(required = false) MultipartFile bankAccountStatement,
            @RequestBody RequirementCreditContext creditContext) {
        return ResponseEntity.ok(service.createCreditContext(creditRequirementId, creditContext, cancelledCheck, bankAccountStatement));
    }

    @GetMapping("/by-credit-requirement/{creditRequirementId}")
    public ResponseEntity<RequirementCreditContext> getCreditContextByRequirement(@PathVariable Long creditRequirementId) {
        return ResponseEntity.ok(service.getByCreditRequirement(creditRequirementId));
    }
}

