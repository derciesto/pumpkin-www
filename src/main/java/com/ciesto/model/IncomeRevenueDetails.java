package com.ciesto.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "income_revenue_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomeRevenueDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "credit_context_id", nullable = false)
    private RequirementCreditContext creditContext;

    private Integer financialYear;
    private Long revenueAmount;
}

