package com.ciesto.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "asset_liabilities")
public class AssetLiabilities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "credit_application_id", nullable = false)
    private CreditApplication creditApplication;

    private String promoterName;
    private String statutoryId;
    private String taxId;
    private Date dob;
    private String mobile;
    private String designation;
    private Date doj;
    private String source;
}
