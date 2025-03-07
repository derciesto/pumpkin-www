package com.ciesto.model.creditRequirement;

import com.ciesto.model.senctioned.IncomeRevenueDetails;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "requirement_credit_context")
@Data
public class RequirementCreditContext {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "credit_requirement_id", nullable = false)
    private CreditRequirement creditRequirement;

    private Integer creditScore;
    private Long totalLoanAmount;
    private Long totalMonthlyEmi;

//    @OneToOne
//    @JoinColumn(name = "primary_bank_account_id")
//    private BankAccount primaryBankAccount;

    private String cancelledCheck;
    private String bankAccountStatement;

//    @OneToMany(mappedBy = "creditContext", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<IncomeRevenueDetails> incomeRevenueDetails;
}

