package com.ciesto.model.creditRequirement;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "proposals")
@Data
public class Proposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lending_institution", nullable = false)
    private String lendingInstitution;

    @Column(name = "loan_duration", nullable = false)
    private Integer loanDuration;

    @Column(name = "loan_amount", nullable = false)
    private Long loanAmount;

    @Column(name = "interest_rate", nullable = false, precision = 10, scale = 2)
    private BigDecimal interestRate;

    @Column(name = "source_channel")
    private String sourceChannel;

    @Column(name = "proposal_date", nullable = false)
    private LocalDate proposalDate;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "credit_requirement_id", nullable = false)
    @JsonBackReference  // to prevent backward simulation
    private CreditRequirement creditRequirement;

}
