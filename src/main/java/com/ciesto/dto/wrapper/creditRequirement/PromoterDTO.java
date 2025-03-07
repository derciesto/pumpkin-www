package com.ciesto.dto.wrapper.creditRequirement;

import com.ciesto.model.creditRequirement.ExecutiveAssociation;
import com.ciesto.model.creditRequirement.SocialReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PromoterDTO {
    private String name;
    private String surname;
    private LocalDate dob;
    private String aadharNumber;
    private String designation;
    private Integer shareholding;
    private Integer age;
    private String pan;
    private String address;
    private Integer yearsInAddress;
    private String din;

    private List<SocialDTO> socialReferences;

    private List<ExecutiveAssociationDTO> executiveAssociations;
}
