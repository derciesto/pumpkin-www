package com.ciesto.model;

import com.ciesto.model.ExecutiveAssociation;
import com.ciesto.model.SocialReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "company_promoter")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"socialReferences", "executiveAssociations"}) // Avoid recursion in logs
public class CompanyPromoter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    @JsonBackReference // Prevents recursion
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
    @JsonManagedReference // Prevent recursion
    private List<SocialReference> socialReferences;

    @OneToMany(mappedBy = "promoter", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference // Prevent recursion
    private List<ExecutiveAssociation> executiveAssociations;
}
