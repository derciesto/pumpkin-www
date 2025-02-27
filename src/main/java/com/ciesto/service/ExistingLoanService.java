package com.ciesto.service;

import com.ciesto.model.CompanyProfile;
import com.ciesto.model.ExistingLoan;
import com.ciesto.repository.CompanyProfileRepository;
import com.ciesto.repository.ExistingLoanRepository;
import com.ciesto.service.utility.ExistingLoanSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ExistingLoanService {

    @Autowired
    private ExistingLoanRepository loanRepository;

    @Autowired
    private CompanyProfileRepository companyRepository;

    public List<ExistingLoan> filterExistingLoans(String lendingInstitute, String loanType,
            LocalDate startDate, LocalDate endDate, String source) {

        Specification<ExistingLoan> spec = ExistingLoanSpecification.filterLoans(
                lendingInstitute, loanType, startDate, endDate, source);

        return loanRepository.findAll(spec);
    }

    public ExistingLoan addExistingLoan(Long companyId, ExistingLoan existingLoan) {
        Optional<CompanyProfile> companyOpt = companyRepository.findById(companyId);

        if (companyOpt.isEmpty()) {
            throw new RuntimeException("Company with ID " + companyId + " not found");
        }

        CompanyProfile company = companyOpt.get();
        existingLoan.setCompany(company);

        return loanRepository.save(existingLoan);
    }
}
