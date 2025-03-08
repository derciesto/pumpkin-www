package com.ciesto.service.creditApplication.utility;

import com.ciesto.dto.wrapper.creditApplication.*;
import com.ciesto.model.creditApplication.ApplicantDetails;
import com.ciesto.model.creditApplication.CollateralSecurity;
import com.ciesto.model.creditApplication.CreditApplication;
import com.ciesto.model.creditApplication.FinancialAssessment;
import com.ciesto.model.creditRequirement.CompanyProfile;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Component
public class LoanApplicationMapper {

    private static DateFormat format = new SimpleDateFormat("yyyy-MM-DD");

    public CreditApplication toCreditApplication(LoanApplicationRequestDTO dto) {
        CreditApplication application = new CreditApplication();
        application.setPurpose(dto.getLoanDetails().getEndUseOfFund());
        application.setLeadtimeAtDiscovery(dto.getLoanDetails().getDateOfFundRequirement());
        application.setLeadAge(dto.getLoanDetails().getLoanDuration());
        application.setSourceChannel(dto.getLoanDetails().getSource());
        application.setStatus(dto.getStatus());
        return application;
    }

    public CompanyProfile toCompanyProfile(CompanyProfileDTO dto) throws ParseException {
        CompanyProfile company = new CompanyProfile();
        company.setName(dto.getName());
        company.setRegistrationType(dto.getRegistrationType());
        company.setZip(dto.getZip());
        company.setCountry(dto.getCountry());
        company.setCity(dto.getCity());
        company.setAddress(dto.getAddress());
        company.setState(dto.getState());
        company.setIncorporationDate(format.parse(dto.getIncorporationDate()));

        company.setGstin(dto.getGstin());
        company.setPanNo(dto.getPanNo());
        company.setMsmeRegistrationNumber(dto.getMsmeRegistrationNumber());
        company.setIndustry(dto.getIndustry());
        company.setSector(dto.getSector());
        return company;
    }

    public ApplicantDetails toApplicantDetails(ApplicantDetailsDTO dto) {
        ApplicantDetails applicant = new ApplicantDetails();
        applicant.setPan(dto.getPan());
        applicant.setEmail(dto.getEmailId());
        applicant.setPhone(dto.getPhone());
        applicant.setState(dto.getState());
        applicant.setEmploymentType(dto.getEmploymentType());
        applicant.setIncomePerAnnum(Integer.parseInt(dto.getIncomePerAnnum()));
        return applicant;
    }

    public FinancialAssessment toFinancialAssessment(FinancialAssessmentDTO dto) {
        FinancialAssessment financial = new FinancialAssessment();
        financial.setFinancialYear(dto.getFinancialYear());
        financial.setMonth(dto.getMonth());
        financial.setTaxesPaid(dto.getTaxesPaid());
        financial.setSource(dto.getSource());
        financial.setReportedSales(dto.getReportedSales());
        financial.setReportedPurchases(dto.getReportedPurchases());
        return financial;
    }

    public CollateralSecurity toCollateralSecurity(CollateralSecurityDTO dto) throws ParseException {
        CollateralSecurity security = new CollateralSecurity();
        security.setBankName(dto.getBankName());
        security.setAccount(dto.getAccount());
        security.setAccountType(dto.getAccountType());
        security.setCurrency(dto.getCurrency());



        security.setOpeningDate(format.parse(dto.getOpeningDate()));
        security.setIfsc(dto.getIfsc());
        security.setAccountHolder(dto.getAccountHolder());
        security.setSource(dto.getSource());
        return security;
    }
}
