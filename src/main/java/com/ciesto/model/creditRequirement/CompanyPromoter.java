package com.ciesto.model.creditRequirement;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "company_promoter")
@Data
@ToString(exclude = {"socialReferences", "executiveAssociations"})
public class CompanyPromoter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id")
    @JsonBackReference
    private CompanyProfile company;

    private String name;
    private String surname;
    private LocalDate dob;
    private String aadharNumber;
    private String designation;
    private Integer shareholding;
    private Integer age;
    private String pan;
    private String address;
    private Integer yearsInAddress;
    private String din;

    @OneToMany(mappedBy = "promoter", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<SocialReference> socialReferences;

    @OneToMany(mappedBy = "promoter", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ExecutiveAssociation> executiveAssociations;
}
