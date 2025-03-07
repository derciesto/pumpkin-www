package com.ciesto.dto.wrapper.creditRequirement;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Address {

    @JsonProperty("address")
    private String address;

    @JsonProperty("city")
    private String city;

    @JsonProperty("state")
    private String state;

    @JsonProperty("zip")
    private String zip;

    @JsonProperty("country")
    private String country;

}
