package com.ciesto.controller.creditRequirement;

import com.ciesto.dto.ApiResponse;
import com.ciesto.model.creditRequirement.RequirementCreditContext;
import com.ciesto.service.creditRequest.RequirementCreditContextService;
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
    public ResponseEntity<ApiResponse<RequirementCreditContext>> createCreditContext(
            @RequestParam Long creditRequirementId,
            @RequestParam(required = false) MultipartFile cancelledCheck,
            @RequestParam(required = false) MultipartFile bankAccountStatement,
            @RequestBody RequirementCreditContext creditContext) {
        try {
            RequirementCreditContext createdContext = service.createCreditContext(creditRequirementId, creditContext, cancelledCheck, bankAccountStatement);
            return ResponseEntity.ok(ApiResponse.success(createdContext));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error creating credit context: " + e.getMessage()));
        }
    }

    @GetMapping("/by-credit-requirement/{creditRequirementId}")
    public ResponseEntity<ApiResponse<RequirementCreditContext>> getCreditContextByRequirement(@PathVariable Long creditRequirementId) {
        try {
            RequirementCreditContext creditContext = service.getByCreditRequirement(creditRequirementId);
            return ResponseEntity.ok(ApiResponse.success(creditContext));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error fetching credit context: " + e.getMessage()));
        }
    }
}
