package com.ciesto.model.creditApplication;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "applicant_details")
@Data
public class ApplicantDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "credit_application_id", nullable = false)
    private CreditApplication creditApplication;

    @Column(unique = true, nullable = false)
    private String pan;

    private String fullName;
    private String email;
    private String phone;
    private String employmentType;
    private Integer incomePerAnnum;
    private String state;
    private String panDocument;

    @Column(updatable = false)
    private LocalDateTime insertTimestamp = LocalDateTime.now();
}
