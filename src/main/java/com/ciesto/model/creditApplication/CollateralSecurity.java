package com.ciesto.model.creditApplication;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "collateral_security")
public class CollateralSecurity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "credit_application_id", nullable = false)
    private CreditApplication creditApplication;

    private String bankName;
    private String accountHolder;
    private String account;
    private String ifsc;
    private String accountType;
    private String currency;
    private Date openingDate;
    private String source;
}
