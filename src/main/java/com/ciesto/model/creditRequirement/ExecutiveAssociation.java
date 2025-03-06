package com.ciesto.model.creditRequirement;

import com.ciesto.model.creditRequirement.CompanyPromoter;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "executive_association")
@Data
public class ExecutiveAssociation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String associationName;
    private String membershipNumber;
    private LocalDate dateOfJoining;

    @ManyToOne
    @JoinColumn(name = "promoter_id", nullable = false)
    @JsonBackReference // Prevent recursion
    private CompanyPromoter promoter;
}
