package com.ciesto.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "account_assessment")
public class AccountAssessment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "credit_application_id", nullable = false)
    private CreditApplication creditApplication;

    private String financialYear;
    private Integer reportedRevenue;
    private Integer grossIncome;
    private Integer netIncome;
    private Integer taxableIncome;
    private Integer taxablePaid;
    private String source;
}