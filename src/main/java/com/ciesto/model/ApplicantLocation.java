package com.ciesto.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "applicant_location")
@Data
public class ApplicantLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "applicant_id", nullable = false)
    private RequirementApplicantDetails applicant;

    private String address;
    private String city;
    private String state;
    private String zipcode;
    private String country;
}

