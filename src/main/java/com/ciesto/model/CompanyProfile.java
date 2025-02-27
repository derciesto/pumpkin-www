package com.ciesto.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "company_profile")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"promoters"}) // Prevent recursion in logs
public class CompanyProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String registrationType;
    private String gstin;
    private String panNo;
    private String industry;
    private String sector;
    private String msmeRegistrationNumber;
    private String incorporationDate;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String country;
    private LocalDateTime insertedOrUpdatedDate;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<CompanyPromoter> promoters;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference
    private List<ExistingLoan> existingLoans = new ArrayList<>();

}
