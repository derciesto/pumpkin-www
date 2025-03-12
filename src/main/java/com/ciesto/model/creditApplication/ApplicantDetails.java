package com.ciesto.model.creditApplication;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
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

    private String email;
    private String phone;
    @Transient
    private MultipartFile photograph;

    private String identifyProofType;
    private String identificationNumber;
    private String proofOfResidence;
    private String employmentType;
    private String proofOfQualification;
    private Integer incomePerAnnum;
    private String address;
    private String state;
    private String city;
    private String pin;
    //    private String isPhotoGraph;

    @Column(updatable = false)
    private LocalDateTime insertTimestamp = LocalDateTime.now();
}
