package com.ciesto.controller;

import com.ciesto.model.CreditRequirement;
import com.ciesto.service.CreditRequirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credit-requirements")
public class CreditRequirementController {

    @Autowired
    private CreditRequirementService service;

    @GetMapping
    public List<CreditRequirement> getAllCreditRequirements() {
        return service.getAllCreditRequirements();
    }

    @GetMapping("/filter")
    public List<CreditRequirement> filterCreditRequirements(
            @RequestParam(required = false) String companyName,
            @RequestParam(required = false) String purpose,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String identifiedOn,
            @RequestParam(required = false) String sourceChannel) {

        return service.filterCreditRequirements(companyName, purpose, status, identifiedOn, sourceChannel);
    }

    @PostMapping
    public CreditRequirement createCreditRequirement(@RequestBody CreditRequirement creditRequirement) {
        return service.saveCreditRequirement(creditRequirement);
    }

    @DeleteMapping("/{id}")
    public void deleteCreditRequirement(@PathVariable Long id) {
        service.deleteCreditRequirement(id);
    }

    @PostMapping("/add")
    public ResponseEntity<CreditRequirement> addCreditRequirement(@RequestBody CreditRequirement creditRequirement) {
        return ResponseEntity.ok(service.addOrUpdateCreditRequirement(creditRequirement));
    }
}
