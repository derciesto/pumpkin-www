package com.ciesto.dto.wrapper.creditApplication;

import lombok.Data;

@Data
public class FinancialAssessmentDTO {

    private String financialYear;
    private String month;
    private Integer reportedSales;
    private Integer reportedPurchases;
    private String source;
    private Integer taxesPaid;
}

