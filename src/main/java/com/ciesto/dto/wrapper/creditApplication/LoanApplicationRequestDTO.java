package com.ciesto.dto.wrapper.creditApplication;

import lombok.Data;

import java.util.List;

@Data
public class LoanApplicationRequestDTO {

    private CompanyProfileDTO companyProfile;
    private List<AddressDTO> addresses;
    private LoanDetailsDTO loanDetails;
    private ApplicantDetailsDTO applicantDetails;
    private FinancialAssessmentDTO financialAssessment;
    private AccountAssessmentDTO accountAssessment;
    private AssetLiabilitiesDTO assetLiabilities;
    private CollateralSecurityDTO collateralSecurity;
    private String status;
}

