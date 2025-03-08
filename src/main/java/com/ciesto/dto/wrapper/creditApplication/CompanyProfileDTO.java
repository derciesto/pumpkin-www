package com.ciesto.dto.wrapper.creditApplication;

import lombok.Data;

import java.util.List;

@Data
public class CompanyProfileDTO {

    private String name;
    private String registrationType;
    private String gstin;
    private String panNo;
    private String msmeRegistrationNumber;
    private String gsttype;
    private String incorporationDate;
    private String sector;
    private String industry;
    private String applicantType;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String country;
    private List<AddressDTO> addresses;
}

