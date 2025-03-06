package com.ciesto.model.senctioned;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "statement_download")
@Data
public class StatementDownload {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateAvailable;
    private String document;

    @Column(columnDefinition = "TEXT")
    private String description;
    private String owner;

    @ManyToOne
    @JoinColumn(name = "sanctioned_loan_id", nullable = false)
    private SanctionedLoan sanctionedLoan;

}
