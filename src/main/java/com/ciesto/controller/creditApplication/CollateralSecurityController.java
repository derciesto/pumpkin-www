package com.ciesto.controller.creditApplication;

import com.ciesto.dto.ApiResponse;
import com.ciesto.model.creditApplication.CollateralSecurity;
import com.ciesto.service.creditApplication.CollateralSecurityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/collateral-security")
public class CollateralSecurityController {
    private final CollateralSecurityService service;

    public CollateralSecurityController(CollateralSecurityService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CollateralSecurity>> create(
            @RequestParam Long creditApplicationId,
            @RequestBody CollateralSecurity entity) {
        try {
            CollateralSecurity createdEntity = service.save(creditApplicationId, entity);
            return ResponseEntity.ok(ApiResponse.success(createdEntity));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error creating collateral security: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CollateralSecurity>> getById(@PathVariable Long id) {
        try {
            CollateralSecurity entity = service.getById(id);
            return ResponseEntity.ok(ApiResponse.success(entity));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error fetching collateral security: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build(); // Void response doesn't need to be wrapped in ApiResponse
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error deleting collateral security: " + e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<CollateralSecurity>>> getAll(Pageable pageable) {
        try {
            Page<CollateralSecurity> page = service.getAll(pageable);
            return ResponseEntity.ok(ApiResponse.success(page));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error fetching collateral securities: " + e.getMessage()));
        }
    }
}
