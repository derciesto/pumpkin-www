package com.ciesto.dto.wrapper.creditRequirement;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PromoterDTO {

    @JsonProperty("name")
    private String name;

    @JsonProperty("surname")
    private String surname;

    @JsonProperty("dob")
    private LocalDate dob;

    @JsonProperty("aadharNumber")
    private String aadharNumber;

    @JsonProperty("designation")
    private String designation;

    @JsonProperty("shareholding")
    private Integer shareholding;

    @JsonProperty("age")
    private Integer age;

    @JsonProperty("pan")
    private String pan;

    @JsonProperty("address")
    private String address;

    @JsonProperty("yearsInAddress")
    private Integer yearsInAddress;

    @JsonProperty("din")
    private String din;

    @JsonProperty("socialReferences")
    private List<SocialDTO> socialReferences;

    @JsonProperty("executiveAssociations")
    private List<ExecutiveAssociationDTO> executiveAssociations;
}
