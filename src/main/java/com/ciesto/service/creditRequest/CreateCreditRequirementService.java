package com.ciesto.service.creditRequest;

import com.ciesto.dto.wrapper.creditRequirement.*;
import com.ciesto.model.creditRequirement.*;
import com.ciesto.repository.creditRequirement.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CreateCreditRequirementService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private CreditRequirementRepository creditRequirementRepository;

    @Autowired
    private CompanyProfileRepository companyProfileRepository;

    @Autowired
    private CompanyPromoterRepository companyPromoterRepository;

    @Autowired
    private RequirementCreditContextRepository requirementCreditContextRepository;

    public void createRequirement(LoanRequest loanRequest) {
        mapLoanRequest(loanRequest);
    }

    private void mapLoanRequest(LoanRequest loanRequest) {
        CreditRequirement requirement = new CreditRequirement();
        requirement.setLoanFormat(loanRequest.getLoanFormat());
        requirement.setRequirementDescription(loanRequest.getRequirementDescription());
        requirement.setEndUseOfFund(loanRequest.getEndUseOfFund());
        requirement.setDateOfFundRequirement(loanRequest.getDateOfFundRequirement());
        requirement.setLoanAmount(loanRequest.getLoanAmount());
        requirement.setLoanDuration(loanRequest.getLoanDuration());
        requirement.setCollateralType(loanRequest.getCollateralType());
        requirement.setPurchaseDate(loanRequest.getPurchaseDate());
        requirement.setOwnedBy(loanRequest.getOwnedBy());
        requirement.setSourceChannel(loanRequest.getSourceChannel());
        requirement.setStatus(loanRequest.getStatus());
        requirement.setPurchaseValue(loanRequest.getPurchaseValue());
        requirement.setMarketValue(loanRequest.getMarketValue());
        requirement.setDescription(loanRequest.getDescription());

        requirement.setCompany(mapCompany(loanRequest.getCompany()));
        CreditRequirement saved = creditRequirementRepository.save(requirement);

        mapCreditContext(loanRequest.getCreditContext(),saved);


    }


    private CompanyProfile mapCompany(List<Company> companies) {
        CompanyProfile save = new CompanyProfile();
        for (Company company : companies) {
            CompanyProfile companyObj = new CompanyProfile();
            companyObj.setAddress(company.getAddress());
            companyObj.setName(company.getName());
            companyObj.setGstin(company.getGstin());
            companyObj.setRegistrationType(company.getRegistrationType());
            companyObj.setGstin(company.getGstin());
            companyObj.setPanNo(company.getPanNo());
            companyObj.setIndustry(company.getIndustry());
            companyObj.setSector(company.getSector());
            companyObj.setMsmeRegistrationNumber(company.getMsmeRegistrationNumber());
            companyObj.setIncorporationDate(company.getIncorporationDate());
            companyObj.setAddress(company.getAddress());
            companyObj.setCity(company.getCity());
            companyObj.setState(companyObj.getState());
            companyObj.setZip(company.getZip());
            companyObj.setCountry(company.getCountry());
            companyObj.setInsertedOrUpdatedDate(LocalDateTime.now());

            companyObj.setPromoters(mapPromoters(company.getPromoters()));
//            mapAddress(company.getAddresses());

            save = companyProfileRepository.save(companyObj);
        }
        return save;
    }

    private List<CompanyPromoter> mapPromoters(List<PromoterDTO> promoters) {
        List<CompanyPromoter> comProm = new ArrayList<>();
        for (PromoterDTO promoter : promoters) {
            CompanyPromoter prom = new CompanyPromoter();

            prom.setName(promoter.getName());
            prom.setSurname(promoter.getSurname());
            prom.setDob(promoter.getDob());
            prom.setAadharNumber(promoter.getAadharNumber());
            prom.setDesignation(promoter.getDesignation());
            prom.setShareholding(promoter.getShareholding());
            prom.setAge(promoter.getAge());
            prom.setPan(promoter.getPan());
            prom.setAddress(promoter.getAddress());
            prom.setYearsInAddress(promoter.getYearsInAddress());
            prom.setDin(promoter.getDin());

            prom.setSocialReferences(mapSocial(promoter.getSocialReferences()));

            prom.setExecutiveAssociations(mapExecutiveAssociate(promoter.getExecutiveAssociations()));
            comProm.add(prom);
        }

        return comProm;
    }

    private List<SocialReference> mapSocial(List<SocialDTO> socialReferences) {
        List<SocialReference> socials = new ArrayList<>();
        for (SocialDTO socialReference : socialReferences) {
            SocialReference sr = new SocialReference();
            sr.setUrl(socialReference.getUrl());
            sr.setSocialNetworkName(socialReference.getSocialNetworkName());
            socials.add(sr);
        }
        return socials;
    }

    private List<ExecutiveAssociation> mapExecutiveAssociate(List<ExecutiveAssociationDTO> executiveAssociations) {
        List<ExecutiveAssociation> eas = new ArrayList<>();

        for (ExecutiveAssociationDTO ea : executiveAssociations) {
            ExecutiveAssociation eao = new ExecutiveAssociation();
            eao.setAssociationName(ea.getAssociationName());
            eao.setMembershipNumber(ea.getMembershipNumber());
            eao.setDateOfJoining(ea.getDateOfJoining());
            eas.add(eao);
        }
        return eas;
    }

    private void mapAddress(List<Address> addresses) {

    }

    private void mapCreditContext(CreditContext creditContext,CreditRequirement requirement) {
        RequirementCreditContext req = new RequirementCreditContext();

        req.setCreditScore(creditContext.getCreditScore());
        req.setTotalLoanAmount(creditContext.getTotalLoanAmount());
        req.setTotalMonthlyEmi(creditContext.getTotalMonthlyEmi());

        req.setCreditRequirement(requirement);

        RequirementCreditContext persisted = requirementCreditContextRepository.save(req);
        mapBank(creditContext.getBank(),persisted);

    }

    private List<BankAccount> mapBank(List<Bank> banks,RequirementCreditContext cont) {
        List<BankAccount> bankAccounts = new ArrayList<>();

        banks.forEach(bank -> {
            BankAccount ba = new BankAccount();
            ba.setBankName(bank.getBankName());
            ba.setCreditContext(cont);
            ba.setAccountNumber(bank.getAccountNumber());
            ba.setAccountType(bank.getAccountType());
            ba.setIfscCode(bank.getIfscCode());
            bankAccounts.add(ba);
        });

        return bankAccountRepository.saveAll(bankAccounts);
    }
}
