package com.ciesto.model.senctioned;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Entity
@Table(name = "registered_payment_method")
@Data
public class RegisteredPaymentMethod implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long accountId;
    private String bankAccNo;
    private String bankAccType;
    private String bankIfsc;
    private String bankHolder;
    private String paymentMode;
    private String status;

    @ManyToOne
    @JoinColumn(name = "sanctioned_loan_id", nullable = false)
    @JsonBackReference
    private SanctionedLoan sanctionedLoan;
}

