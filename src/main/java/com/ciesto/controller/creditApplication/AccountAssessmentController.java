package com.ciesto.controller.creditApplication;

import com.ciesto.dto.ApiResponse;
import com.ciesto.model.creditApplication.AccountAssessment;
import com.ciesto.service.creditApplication.AccountAssessmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/account-assessment")
public class AccountAssessmentController {

    private final AccountAssessmentService service;

    public AccountAssessmentController(AccountAssessmentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AccountAssessment>> create(
            @RequestParam Long creditApplicationId,
            @RequestBody AccountAssessment entity) {
        try {
            AccountAssessment createdEntity = service.save(creditApplicationId, entity);
            return ResponseEntity.ok(ApiResponse.success(createdEntity));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error creating account assessment: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AccountAssessment>> getById(@PathVariable Long id) {
        try {
            AccountAssessment entity = service.getById(id);
            return ResponseEntity.ok(ApiResponse.success(entity));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error fetching account assessment: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();  // Void response doesn't need to be wrapped in ApiResponse
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error deleting account assessment: " + e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<AccountAssessment>>> getAll(Pageable pageable) {
        try {
            Page<AccountAssessment> page = service.getAll(pageable);
            return ResponseEntity.ok(ApiResponse.success(page));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error fetching account assessments: " + e.getMessage()));
        }
    }
}
