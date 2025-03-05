package com.ciesto.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bank_account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "credit_context_id", nullable = false)
    private RequirementCreditContext creditContext;

    private String accountNumber;
    private String bankName;
    private String ifscCode;
    private String accountHolderName;
    private String accountType;
}
