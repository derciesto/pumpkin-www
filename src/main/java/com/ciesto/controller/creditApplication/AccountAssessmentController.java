package com.ciesto.controller.creditApplication;

import com.ciesto.model.AccountAssessment;
import com.ciesto.service.creditApplicatoin.AccountAssessmentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/account-assessment")
public class AccountAssessmentController {
    private final AccountAssessmentService service;

    public AccountAssessmentController(AccountAssessmentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AccountAssessment> create(@RequestParam Long creditApplicationId,@RequestBody AccountAssessment entity) {
        return ResponseEntity.ok(service.save(creditApplicationId,entity));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountAssessment> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<AccountAssessment>> getAll(Pageable pageable) {
        return ResponseEntity.ok(service.getAll(pageable));
    }
}

