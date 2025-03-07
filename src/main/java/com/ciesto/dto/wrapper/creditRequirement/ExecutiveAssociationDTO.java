package com.ciesto.dto.wrapper.creditRequirement;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ExecutiveAssociationDTO {
    private String associationName;
    private String membershipNumber;
    private LocalDate dateOfJoining;
}
