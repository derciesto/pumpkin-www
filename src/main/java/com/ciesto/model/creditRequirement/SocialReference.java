package com.ciesto.model.creditRequirement;

import com.ciesto.model.creditRequirement.CompanyPromoter;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "social_reference")
@Data
public class SocialReference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String socialNetworkName;
    private String url;

    @ManyToOne
    @JoinColumn(name = "promoter_id", nullable = false)
    @JsonBackReference // Prevent recursion
    private CompanyPromoter promoter;
}
