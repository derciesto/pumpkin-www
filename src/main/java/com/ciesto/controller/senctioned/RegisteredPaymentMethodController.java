package com.ciesto.controller.senctioned;

import com.ciesto.dto.ApiResponse;
import com.ciesto.model.senctioned.RegisteredPaymentMethod;
import com.ciesto.service.senctioned.RegisteredPaymentMethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment-methods")
@RequiredArgsConstructor
public class RegisteredPaymentMethodController {

    private final RegisteredPaymentMethodService service;

    @PostMapping
    public ResponseEntity<ApiResponse<RegisteredPaymentMethod>> createPaymentMethod(
            @RequestBody RegisteredPaymentMethod paymentMethod,
            @RequestParam Long sanctionedLoanId) {
        try {
            RegisteredPaymentMethod createdPaymentMethod = service.createPaymentMethod(paymentMethod, sanctionedLoanId);
            return ResponseEntity.ok(ApiResponse.success(createdPaymentMethod));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error creating payment method: " + e.getMessage()));
        }
    }

    @GetMapping("/by-loan/{sanctionedLoanId}")
    public ResponseEntity<ApiResponse<List<RegisteredPaymentMethod>>> getBySanctionedLoan(@PathVariable Long sanctionedLoanId) {
        try {
            List<RegisteredPaymentMethod> paymentMethods = service.getBySanctionedLoan(sanctionedLoanId);
            return ResponseEntity.ok(ApiResponse.success(paymentMethods));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error fetching payment methods: " + e.getMessage()));
        }
    }
}
