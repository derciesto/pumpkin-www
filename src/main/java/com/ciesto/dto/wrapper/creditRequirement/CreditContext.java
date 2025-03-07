package com.ciesto.dto.wrapper.creditRequirement;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CreditContext {

    @JsonProperty("creditScore")
    private Integer creditScore;
    @JsonProperty("totalLoanAmount")
    private Long totalLoanAmount;
    @JsonProperty("totalMonthlyEmi")
    private Long totalMonthlyEmi;
    @JsonProperty("cancelledCheck")
    private String cancelledCheck;
    @JsonProperty("bankAccountStatement")
    private String bankAccountStatement;

    @JsonProperty("bank")
    private List<Bank> bank;
}
