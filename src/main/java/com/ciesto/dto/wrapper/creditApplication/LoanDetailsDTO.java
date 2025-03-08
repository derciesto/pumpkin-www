package com.ciesto.dto.wrapper.creditApplication;

import lombok.Data;

@Data
public class LoanDetailsDTO {

    private String endUseOfFund;
    private String dateOfFundRequirement;
    private String loanAmount;
    private String loanDuration;
    private String collateralType;
    private String purchaseDate;
    private String ownedBy;
    private String purchaseValue;
    private String marketValue;
    private String description;
    private String source;
    private String identifiedOn;
    private String sourceChannel;
}

