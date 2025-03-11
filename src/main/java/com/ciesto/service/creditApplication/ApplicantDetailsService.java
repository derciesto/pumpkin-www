package com.ciesto.service.creditApplication;

import com.ciesto.common.customException.ResourceNotFoundException;
import com.ciesto.model.creditApplication.ApplicantDetails;
import com.ciesto.model.creditApplication.CreditApplication;
import com.ciesto.repository.creditApplication.ApplicantDetailsRepository;
import com.ciesto.repository.creditApplication.CreditApplicationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class ApplicantDetailsService {

    @Autowired
    private ApplicantDetailsRepository repository;

    @Autowired
    private CreditApplicationRepository creditApplicationRepository;

    @Transactional
    public ApplicantDetails createApplicant(Long creditApplicationId, ApplicantDetails applicant) {
        CreditApplication creditApplication = creditApplicationRepository.findById(creditApplicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Credit Application not found", 404L));
        applicant.setCreditApplication(creditApplication);
        return repository.save(applicant);
    }

    public ApplicantDetails getApplicantById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Applicant not found", 404L));
    }

    public List<ApplicantDetails> getApplicantByApplicationId(Long id) {
        return repository.findByCreditApplicationId(id);
    }

    public List<ApplicantDetails> getApplicantsWithFilters(String pan, String phone, String employmentType, String state) {
        List<ApplicantDetails> applicants = repository.findAll();

        return applicants.stream()
                .filter(createApplicantPredicate(pan, phone, employmentType, state))
                .collect(Collectors.toList());
    }

    private Predicate<ApplicantDetails> createApplicantPredicate(String pan, String phone, String employmentType, String state) {
        return applicant -> (StringUtils.isEmpty(pan) || applicant.getPan().equalsIgnoreCase(pan))
                && (StringUtils.isEmpty(phone) || applicant.getPhone().equalsIgnoreCase(phone))
                && (StringUtils.isEmpty(employmentType) || applicant.getEmploymentType().equalsIgnoreCase(employmentType))
                && (StringUtils.isEmpty(state) || applicant.getState().equalsIgnoreCase(state));
    }

    @Transactional
    public ApplicantDetails updateApplicant(Long id, ApplicantDetails updatedApplicant) {
        ApplicantDetails existingApplicant = getApplicantById(id);

        existingApplicant.setFullName(updatedApplicant.getFullName());
        existingApplicant.setEmail(updatedApplicant.getEmail());
        existingApplicant.setPhone(updatedApplicant.getPhone());
        existingApplicant.setEmploymentType(updatedApplicant.getEmploymentType());
        existingApplicant.setIncomePerAnnum(updatedApplicant.getIncomePerAnnum());
        existingApplicant.setState(updatedApplicant.getState());
        existingApplicant.setPanDocument(updatedApplicant.getPanDocument());

        return repository.save(existingApplicant);
    }

    @Transactional
    public void deleteApplicant(Long id) {
        ApplicantDetails applicant = getApplicantById(id);
        repository.delete(applicant);
    }
}
