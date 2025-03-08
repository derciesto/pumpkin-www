package com.ciesto.service.creditApplication;

import com.ciesto.dto.wrapper.creditApplication.LoanApplicationRequestDTO;
import com.ciesto.model.creditApplication.CreditApplication;
import com.ciesto.repository.creditApplication.*;
import com.ciesto.repository.creditRequirement.CompanyProfileRepository;
import com.ciesto.service.creditApplication.utility.LoanApplicationMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.ciesto.model.creditApplication.ApplicantDetails;
import com.ciesto.model.creditApplication.CollateralSecurity;
import com.ciesto.model.creditApplication.FinancialAssessment;
import com.ciesto.model.creditRequirement.CompanyProfile;

import java.text.ParseException;

@Service
@RequiredArgsConstructor
public class LoanApplicationService {

    private final CreditApplicationRepository creditApplicationRepository;
    private final CompanyProfileRepository companyProfileRepository;
    private final ApplicantDetailsRepository applicantDetailsRepository;
    private final AssetLiabilitiesRepository assetLiabilitiesRepository;
    private final FinancialAssessmentRepository financialAssessmentRepository;
    private final CollateralSecurityRepository collateralSecurityRepository;
    private final LoanApplicationMapper mapper;

    @Transactional
    public CreditApplication createApplication(LoanApplicationRequestDTO request) throws ParseException {

        // Convert to Entity
        CreditApplication creditApplication = mapper.toCreditApplication(request);
        CompanyProfile companyProfile = mapper.toCompanyProfile(request.getCompanyProfile());
        ApplicantDetails applicantDetails = mapper.toApplicantDetails(request.getApplicantDetails());
        FinancialAssessment financialAssessment = mapper.toFinancialAssessment(request.getFinancialAssessment());
        CollateralSecurity collateralSecurity = mapper.toCollateralSecurity(request.getCollateralSecurity());

        companyProfileRepository.save(companyProfile);

        creditApplication.setCompany(companyProfile);

        creditApplicationRepository.save(creditApplication);

        applicantDetails.setCreditApplication(creditApplication);
        applicantDetailsRepository.save(applicantDetails);

        financialAssessment.setCreditApplication(creditApplication);
        financialAssessmentRepository.save(financialAssessment);

        collateralSecurity.setCreditApplication(creditApplication);
        collateralSecurityRepository.save(collateralSecurity);

        return creditApplication;
    }
}

