package com.ciesto.model.creditApplication;

import com.ciesto.model.creditApplication.CreditApplication;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "financial_assessment")
@Data
public class FinancialAssessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "credit_application_id", nullable = false)
    private CreditApplication creditApplication;

    private String financialYear;
    private String month;
    private Integer reportedSales;
    private Integer reportedPurchases;
    private Integer taxesPaid;
    private String source;
}
