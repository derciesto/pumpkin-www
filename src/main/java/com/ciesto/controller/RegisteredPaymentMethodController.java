package com.ciesto.controller;

import com.ciesto.model.RegisteredPaymentMethod;
import com.ciesto.service.RegisteredPaymentMethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment-methods")
@RequiredArgsConstructor
public class RegisteredPaymentMethodController {
    private final RegisteredPaymentMethodService service;

    @PostMapping
    public RegisteredPaymentMethod createPaymentMethod(@RequestBody RegisteredPaymentMethod paymentMethod,
                                                       @RequestParam Long sanctionedLoanId) {
        return service.createPaymentMethod(paymentMethod, sanctionedLoanId);
    }

    @GetMapping("/by-loan/{sanctionedLoanId}")
    public List<RegisteredPaymentMethod> getBySanctionedLoan(@PathVariable Long sanctionedLoanId) {
        return service.getBySanctionedLoan(sanctionedLoanId);
    }
}

