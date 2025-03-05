package com.ciesto.controller;

import com.ciesto.model.BankAccount;
import com.ciesto.service.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bank-accounts")
@RequiredArgsConstructor
public class BankAccountController {
    private final BankAccountService service;

    @PostMapping
    public ResponseEntity<BankAccount> createBankAccount(
            @RequestParam Long creditContextId,
            @RequestBody BankAccount bankAccount) {
        return ResponseEntity.ok(service.createBankAccount(creditContextId, bankAccount));
    }

    @GetMapping("/by-credit-context/{creditContextId}")
    public ResponseEntity<List<BankAccount>> getBankAccountsByCreditContext(@PathVariable Long creditContextId) {
        return ResponseEntity.ok(service.getByCreditContext(creditContextId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BankAccount> updateBankAccount(@PathVariable Long id, @RequestBody BankAccount bankAccount) {
        return ResponseEntity.ok(service.updateBankAccount(id, bankAccount));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBankAccount(@PathVariable Long id) {
        service.deleteBankAccount(id);
        return ResponseEntity.noContent().build();
    }
}

