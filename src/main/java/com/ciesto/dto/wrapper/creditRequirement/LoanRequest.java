package com.ciesto.dto.wrapper.creditRequirement;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class LoanRequest {

    @JsonProperty("loanFormat")
    private String loanFormat;

    @JsonProperty("requirementDescription")
    private String requirementDescription;

    @JsonProperty("endUseOfFund")
    private String endUseOfFund;

    @JsonProperty("dateOfFundRequirement")
    private LocalDate dateOfFundRequirement;

    @JsonProperty("loanAmount")
    private Integer loanAmount;

    @JsonProperty("loanDuration")
    private Integer loanDuration;

    @JsonProperty("collateralType")
    private String collateralType;

    @JsonProperty("purchaseDate")
    private LocalDate purchaseDate;

    @JsonProperty("ownedBy")
    private String ownedBy;

    @JsonProperty("status")
    private String status;

    @JsonProperty("sourceChannel")
    private String sourceChannel;

    @JsonProperty("purchaseValue")
    private Integer purchaseValue;

    @JsonProperty("marketValue")
    private Integer marketValue;

    @JsonProperty("description")
    private String description;

//    @JsonProperty("fy-2223")
//    private String fy2223;
//
//    @JsonProperty("fy-2324")
//    private String fy2324;
//
//    @JsonProperty("fy-2425")
//    private String fy2425;

//    @JsonProperty("terms")
//    private String terms;
//
//    @JsonProperty("lenderInstitutions")
//    private String lenderInstitutions;
//
//    @JsonProperty("isSubscribe")
//    private String isSubscribe;
//
//    @JsonProperty("otp")
//    private String otp;
//
//    @JsonProperty("adharNumber")
//    private String adharNumber;
//
//    @JsonProperty("panDoc")
//    private String panDoc;
//
//    @JsonProperty("adharDoc")
//    private String adharDoc;
//
//    @JsonProperty("msmeDoc")
//    private String msmeDoc;
//
//    @JsonProperty("bankDoc")
//    private String bankDoc;
//
//    @JsonProperty("chequeDoc")
//    private String chequeDoc;
//

    @JsonProperty("address")
    private List<Address> address;

    @JsonProperty("creditContext")
    private CreditContext creditContext;

    @JsonProperty("company")
    private List<Company> company;

}

