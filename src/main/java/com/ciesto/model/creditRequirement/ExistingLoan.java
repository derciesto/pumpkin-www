package com.ciesto.model.creditRequirement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "existing_loans")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExistingLoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    @JsonIgnore
    private CompanyProfile company;

    @Column(nullable = false, length = 255)
    private String lendingInstitute;

    @Column(nullable = false)
    private Long loanAmount;

    @Column(nullable = false)
    private String loanType;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private Long emi;

    @Column(nullable = false)
    private Integer tenureMonths;

    @Column(nullable = false)
    private String source;

    public Long getCompanyId() {
        return (company != null) ? company.getId() : null;
    }
}
