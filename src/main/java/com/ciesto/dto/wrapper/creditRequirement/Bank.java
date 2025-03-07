package com.ciesto.dto.wrapper.creditRequirement;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Bank {

    @JsonProperty("accountNumber")
    private String accountNumber;

    @JsonProperty("bankName")
    private String bankName;

    @JsonProperty("ifscCode")
    private String ifscCode;

    @JsonProperty("accountHolderName")
    private String accountHolderName;

    @JsonProperty("accountType")
    private String accountType;

}

