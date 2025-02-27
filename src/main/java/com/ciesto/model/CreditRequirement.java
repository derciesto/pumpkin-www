package com.ciesto.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "credit_requirement")
@Data
@ToString
public class CreditRequirement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyProfile company;

    private String loadFormat;

    @Column(columnDefinition = "TEXT")
    private String requirementDescription;

    @Column(columnDefinition = "TEXT")
    private String endUseOfFund;

    private LocalDate dateOfFundRequirement;

    private Integer loanAmount;

    private Integer loanDuration;

    private String collateralType;

    private LocalDate purchaseDate;

    private String ownedBy;

    private Integer purchaseValue;

    private Integer marketValue;

    @Column(columnDefinition = "TEXT")
    private String description;
}