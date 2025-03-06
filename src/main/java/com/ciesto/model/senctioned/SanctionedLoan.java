package com.ciesto.model.senctioned;

import com.ciesto.model.creditApplication.CreditApplication;
import com.ciesto.model.senctioned.DisbursementDetail;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "sanctioned_loans")
public class SanctionedLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    @OneToOne
    @JoinColumn(name = "credit_application_id", nullable = false, unique = true)
    private CreditApplication creditApplication;

    private Long sanctionedAmount;
    private Long disbursedAmount;
    private BigDecimal roi;
    private String product;
    private Integer tenure;
    private Long emi;
    private Long overdueAmount;
    private String status;

    private Integer numberOutstandingRequest;
    private Long amountOutstandingRequest;
    private Integer daysOutstandingRequest;

    @OneToMany(mappedBy = "sanctionedLoan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DisbursementDetail> disbursementDetails;
}
