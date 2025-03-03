package com.ciesto.service.creditApplicatoin.utility;

import com.ciesto.model.AccountAssessment;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class AccountAssessmentSpecification {

    public static Specification<AccountAssessment> filterBy(String financialYear, Integer taxablePaid, String source) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (financialYear != null) {
                predicates.add(criteriaBuilder.equal(root.get("financialYear"), financialYear));
            }
            if (taxablePaid != null) {
                predicates.add(criteriaBuilder.equal(root.get("taxablePaid"), taxablePaid));
            }
            if (source != null) {
                predicates.add(criteriaBuilder.like(root.get("source"), "%" + source + "%"));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

