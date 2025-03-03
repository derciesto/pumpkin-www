package com.ciesto.controller.creditApplication;


import com.ciesto.model.CollateralSecurity;
import com.ciesto.service.creditApplicatoin.CollateralSecurityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/collateral-security")
public class CollateralSecurityController {
    private final CollateralSecurityService service;

    public CollateralSecurityController(CollateralSecurityService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CollateralSecurity> create(@RequestParam Long creditApplicationId,@RequestBody CollateralSecurity entity) {
        return ResponseEntity.ok(service.save(creditApplicationId,entity));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CollateralSecurity> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<CollateralSecurity>> getAll(Pageable pageable) {
        return ResponseEntity.ok(service.getAll(pageable));
    }
}