package com.ciesto.controller.creditApplication;

import com.ciesto.model.CreditApplication;
import com.ciesto.service.creditApplicatoin.CreditApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/credit-applications")
public class CreditApplicationController {

    private final CreditApplicationService service;

    public CreditApplicationController(CreditApplicationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> create( @RequestParam Long companyId,
                                     @RequestBody CreditApplication creditApplication) {

        try {
            CreditApplication savedCreditApplication = service.createCreditApplication(companyId, creditApplication);
            return ResponseEntity.ok(savedCreditApplication);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditApplication> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<CreditApplication>> getAllFiltered(
            @RequestParam(required = false) String companyName,
            @RequestParam(required = false) String purpose,
            @RequestParam(required = false) String identifiedOn,
            @RequestParam(required = false) String sourceChannel,
            @RequestParam(required = false) String status
    ) {
        return ResponseEntity.ok(service.getAllFiltered(companyName, purpose, identifiedOn, sourceChannel, status));
    }
}
