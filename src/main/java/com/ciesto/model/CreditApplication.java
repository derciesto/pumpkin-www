package com.ciesto.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "credit_application")
@Data
public class CreditApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyProfile company;

    private String purpose;
    private String leadtimeAtDiscovery;
    private String leadAge;
    private String identifiedOn;
    private String sourceChannel;
    private String status;

    @Column(updatable = false)
    private LocalDateTime insertTimestamp = LocalDateTime.now();
}
