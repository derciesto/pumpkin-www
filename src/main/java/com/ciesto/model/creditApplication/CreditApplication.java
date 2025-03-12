package com.ciesto.model.creditApplication;

import com.ciesto.model.creditRequirement.CompanyProfile;
import com.ciesto.model.creditRequirement.CreditRequirement;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

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

    @Transient
    private List<CreditRequirement> requirement;

    @Column(updatable = false)
    private LocalDateTime insertTimestamp = LocalDateTime.now();
}
