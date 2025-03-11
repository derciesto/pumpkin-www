package com.ciesto.controller.validation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/otp")
public class ValidationController {

    @GetMapping("/sms")
    public void smsOtpRequest() {

    }

    @GetMapping("/email")
    public void emailOtpRequest() {

    }

    @PostMapping("/sms/validate")
    public void smsValidateOtp() {

    }

    @PostMapping("/email/validate")
    public void emailValidateOtp() {

    }
}
