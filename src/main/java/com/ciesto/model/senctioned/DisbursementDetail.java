package com.ciesto.model.senctioned;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "disbursement_details")
public class DisbursementDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long srNo;

    @ManyToOne
    @JoinColumn(name = "loan_id", nullable = false)
    @JsonBackReference
    private SanctionedLoan sanctionedLoan;

    private LocalDate requestedDate;
    private Long requestAmount;
    private LocalDate dueDate;
    private String requestedBy;
    private String designation;
    private String document;
    private String status;
}

