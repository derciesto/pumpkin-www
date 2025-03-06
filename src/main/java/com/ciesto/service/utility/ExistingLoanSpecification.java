package com.ciesto.service.utility;

import com.ciesto.model.creditRequirement.ExistingLoan;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.ciesto.common.Constants.*;

public class ExistingLoanSpecification {
    public static Specification<ExistingLoan> filterLoans(
            String lendingInstitute, String loanType,
            LocalDate startDate, LocalDate endDate, String source) {

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (lendingInstitute != null && !lendingInstitute.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get(LENDING_INSTITUTION), lendingInstitute));
            }
            if (loanType != null && !loanType.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get(LOAN_TYPE), loanType));
            }
            if (startDate != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(START_DATE), startDate));
            }
            if (endDate != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(END_DATE), endDate));
            }
            if (source != null && !source.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get(SOURCE), source));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
