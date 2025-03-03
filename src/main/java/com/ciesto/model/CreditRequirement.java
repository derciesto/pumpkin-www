package com.ciesto.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    private String loanFormat;

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
    private String sourceChannel;
    private String status;

    private Integer purchaseValue;

    private Integer marketValue;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "creditRequirement", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JsonBackReference
    private List<Proposal> proposals = new ArrayList<>();
}