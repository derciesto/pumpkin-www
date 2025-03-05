package com.ciesto.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "requirement_applicant_details")
@Data
public class RequirementApplicantDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "credit_requirement_id", nullable = false)
    private CreditRequirement creditRequirement;

    private String pan;
    private String fullName;
    private String applicantName;
    private String applicantType;
    private String gstin;
    private String gstType;
    private String sector;
    private String industry;
    private LocalDate dob;
    private String panNo;
    private String msmeRegistrationNumber;
    private String email;
    private String phone;
    private String employmentType;
    private Integer incomePerAnnum;
    private String state;

    // Store only the document paths in the DB
    private String panCard;
    private String aadharCard;
    private String msmeRegistration;

    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime insertTimestamp;

    @OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ApplicantLocation> locations;
}

