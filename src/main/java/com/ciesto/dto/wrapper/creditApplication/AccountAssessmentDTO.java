package com.ciesto.dto.wrapper.creditApplication;

import lombok.Data;

@Data
public class AccountAssessmentDTO {

    private String financialYear;
    private Integer reportedRevenue;
    private Integer grossIncome;
    private Integer netIncome;
    private Integer taxableIncome;
    private Integer taxablePaid;
    private String source;
}

