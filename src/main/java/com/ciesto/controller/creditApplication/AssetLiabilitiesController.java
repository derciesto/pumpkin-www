package com.ciesto.controller.creditApplication;

import com.ciesto.dto.ApiResponse;
import com.ciesto.model.creditApplication.AssetLiabilities;
import com.ciesto.service.creditApplication.AssetLiabilitiesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/asset-liabilities")
public class AssetLiabilitiesController {
    private final AssetLiabilitiesService service;

    public AssetLiabilitiesController(AssetLiabilitiesService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AssetLiabilities>> create(@RequestParam Long creditApplicationId, @RequestBody AssetLiabilities entity) {
        try {
            AssetLiabilities savedEntity = service.save(creditApplicationId, entity);
            return ResponseEntity.ok(ApiResponse.success(savedEntity));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error creating asset liability: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AssetLiabilities>> getById(@PathVariable Long id) {
        try {
            AssetLiabilities entity = service.getById(id);
            return ResponseEntity.ok(ApiResponse.success(entity));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error fetching asset liability: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.ok(ApiResponse.success(null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error deleting asset liability: " + e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<AssetLiabilities>>> getAll(Pageable pageable) {
        try {
            Page<AssetLiabilities> page = service.getAll(pageable);
            return ResponseEntity.ok(ApiResponse.success(page));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error fetching asset liabilities: " + e.getMessage()));
        }
    }
}
