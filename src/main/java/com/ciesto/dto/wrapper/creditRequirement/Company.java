package com.ciesto.dto.wrapper.creditRequirement;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Company {

    @JsonProperty("name")
    private String name;

    @JsonProperty("registrationType")
    private String registrationType;

    @JsonProperty("gstin")
    private String gstin;

    @JsonProperty("gsttype")
    private String gsttype;

    @JsonProperty("panNo")
    private String panNo;

    @JsonProperty("industry")
    private String industry;

    @JsonProperty("sector")
    private String sector;

    @JsonProperty("address")
    private String address;
    @JsonProperty("city")
    private String city;
    @JsonProperty("state")
    private String state;
    @JsonProperty("state")
    private String zip;
    @JsonProperty("country")
    private String country;

    @JsonProperty("msmeRegistrationNumber")
    private String msmeRegistrationNumber;

    @JsonProperty("incorporationDate")
    private Date incorporationDate;

    @JsonProperty("promoters")
    private List<PromoterDTO> promoters;

//    @JsonProperty("addresses")
//    private List<Address> addresses;

}

