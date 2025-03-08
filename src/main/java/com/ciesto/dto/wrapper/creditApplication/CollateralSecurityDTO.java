package com.ciesto.dto.wrapper.creditApplication;

import lombok.Data;

@Data
public class CollateralSecurityDTO {

    private String bankName;
    private String accountHolder;
    private String account;
    private String ifsc;
    private String accountType;
    private String currency;
    private String openingDate;
    private String source;
}

