package com.ciesto.model.senctioned;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "interest_rate_history")
@Data
public class InterestRateHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long accountId;

    @ManyToOne
    @JoinColumn(name = "sanctioned_loan_id", nullable = false)
    @JsonBackReference
    private SanctionedLoan sanctionedLoan;

    private LocalDate roiChangeDate;

    private BigDecimal roiChangeValue;

    private String outstandingPrinciple;

    private Integer roiChangeEmi;

    private String roiChangeTenure;
}

