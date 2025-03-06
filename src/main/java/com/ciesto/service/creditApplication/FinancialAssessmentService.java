package com.ciesto.service.creditApplication;

import com.ciesto.common.customException.ResourceNotFoundException;
import com.ciesto.model.creditApplication.CreditApplication;
import com.ciesto.model.creditApplication.FinancialAssessment;
import com.ciesto.repository.creditApplication.CreditApplicationRepository;
import com.ciesto.repository.creditApplication.FinancialAssessmentRepository;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class FinancialAssessmentService {

    private final FinancialAssessmentRepository financialAssessmentRepository;
    private final CreditApplicationRepository creditApplicationRepository;

    public FinancialAssessmentService(FinancialAssessmentRepository financialAssessmentRepository,
                                      CreditApplicationRepository creditApplicationRepository) {
        this.financialAssessmentRepository = financialAssessmentRepository;
        this.creditApplicationRepository=creditApplicationRepository;
    }

    @Transactional
    public FinancialAssessment createFinancialAssessment(Long creditApplicationId, FinancialAssessment financialAssessment) {
        CreditApplication creditApplication = creditApplicationRepository.findById(creditApplicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Credit Application not found", 404L));

        financialAssessment.setCreditApplication(creditApplication);
        return financialAssessmentRepository.save(financialAssessment);
    }

    public List<FinancialAssessment> getAllFinancialAssessments(String financialYear, String month, Integer reportedSales, Integer reportedPurchases, String source, Integer taxesPaid) {
        Specification<FinancialAssessment> spec = (root, query, cb) -> {
            Predicate predicate = cb.conjunction();

            if (StringUtils.hasText(financialYear)) {
                predicate = cb.and(predicate, cb.equal(root.get("financialYear"), financialYear));
            }
            if (StringUtils.hasText(month)) {
                predicate = cb.and(predicate, cb.equal(root.get("month"), month));
            }
            if (reportedSales != null) {
                predicate = cb.and(predicate, cb.equal(root.get("reportedSales"), reportedSales));
            }
            if (reportedPurchases != null) {
                predicate = cb.and(predicate, cb.equal(root.get("reportedPurchases"), reportedPurchases));
            }
            if (StringUtils.hasText(source)) {
                predicate = cb.and(predicate, cb.equal(root.get("source"), source));
            }
            if (taxesPaid != null) {
                predicate = cb.and(predicate, cb.equal(root.get("taxesPaid"), taxesPaid));
            }

            return predicate;
        };
        return financialAssessmentRepository.findAll(spec);
    }

    public FinancialAssessment getFinancialAssessmentById(Long id) {
        Optional<FinancialAssessment> financialAssessment = financialAssessmentRepository.findById(id);
        return financialAssessment.orElseThrow(() -> new ResourceNotFoundException("FinancialAssessment not found", 404L));
    }

    public FinancialAssessment updateFinancialAssessment(Long id, FinancialAssessment updatedFinancialAssessment) {
        FinancialAssessment existingFinancialAssessment = getFinancialAssessmentById(id);
        existingFinancialAssessment.setFinancialYear(updatedFinancialAssessment.getFinancialYear());
        existingFinancialAssessment.setMonth(updatedFinancialAssessment.getMonth());
        existingFinancialAssessment.setReportedSales(updatedFinancialAssessment.getReportedSales());
        existingFinancialAssessment.setReportedPurchases(updatedFinancialAssessment.getReportedPurchases());
        existingFinancialAssessment.setTaxesPaid(updatedFinancialAssessment.getTaxesPaid());
        existingFinancialAssessment.setSource(updatedFinancialAssessment.getSource());

        return financialAssessmentRepository.save(existingFinancialAssessment);
    }

    public void deleteFinancialAssessment(Long id) {
        FinancialAssessment financialAssessment = getFinancialAssessmentById(id);
        financialAssessmentRepository.delete(financialAssessment);
    }
}

