package com.ciesto.controller.senctioned;

import com.ciesto.dto.ApiResponse;
import com.ciesto.model.creditRequirement.BankAccount;
import com.ciesto.service.creditRequest.BankAccountService;
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
    public ResponseEntity<ApiResponse<BankAccount>> createBankAccount(
            @RequestParam Long creditContextId,
            @RequestBody BankAccount bankAccount) {
        try {
            return ResponseEntity.ok(ApiResponse.success(service.createBankAccount(creditContextId, bankAccount)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error creating bank account: " + e.getMessage()));
        }
    }

    @GetMapping("/by-credit-context/{creditContextId}")
    public ResponseEntity<ApiResponse<List<BankAccount>>> getBankAccountsByCreditContext(@PathVariable Long creditContextId) {
        return ResponseEntity.ok(ApiResponse.success(service.getByCreditContext(creditContextId)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BankAccount>> updateBankAccount(@PathVariable Long id, @RequestBody BankAccount bankAccount) {
        try {
            return ResponseEntity.ok(ApiResponse.success(service.updateBankAccount(id, bankAccount)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error updating bank account: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteBankAccount(@PathVariable Long id) {
        try {
            service.deleteBankAccount(id);
            return ResponseEntity.ok(ApiResponse.success(null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error deleting bank account: " + e.getMessage()));
        }
    }
}

