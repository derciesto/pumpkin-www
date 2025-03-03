package com.ciesto.controller.creditApplication;

import com.ciesto.model.AssetLiabilities;
import com.ciesto.service.creditApplicatoin.AssetLiabilitiesService;
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
    public ResponseEntity<AssetLiabilities> create(@RequestParam Long creditApplicationId,@RequestBody AssetLiabilities entity) {
        return ResponseEntity.ok(service.save(creditApplicationId,entity));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssetLiabilities> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<AssetLiabilities>> getAll(Pageable pageable) {
        return ResponseEntity.ok(service.getAll(pageable));
    }
}
