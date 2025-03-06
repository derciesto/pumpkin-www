package com.ciesto.service.utility;

import com.ciesto.model.creditRequirement.Proposal;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.ciesto.common.Constants.*;

public class ProposalSpecification {

    public static Specification<Proposal> filterProposals(
            String lendingInstitution, Integer loanDuration, Long loanAmount,
            Double interestRate, String sourceChannel, LocalDate proposalDate, String status) {

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (lendingInstitution != null && !lendingInstitution.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get(LENDING_INSTITUTION), PERCENT_SYMBOL + lendingInstitution + PERCENT_SYMBOL));
            }
            if (loanDuration != null) {
                predicates.add(criteriaBuilder.equal(root.get(LOAN_DURATION), loanDuration));
            }
            if (loanAmount != null) {
                predicates.add(criteriaBuilder.equal(root.get(LOAN_AMOUNT), loanAmount));
            }
            if (interestRate != null) {
                predicates.add(criteriaBuilder.equal(root.get(INTEREST_RATE), BigDecimal.valueOf(interestRate)));
            }
            if (sourceChannel != null && !sourceChannel.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get(SOURCE_CHANNEL), PERCENT_SYMBOL + sourceChannel + PERCENT_SYMBOL));
            }
            if (proposalDate != null) {
                predicates.add(criteriaBuilder.equal(root.get(PROPOSAL_DATE), proposalDate));
            }
            if (status != null && !status.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get(STATUS), PERCENT_SYMBOL + status + PERCENT_SYMBOL));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
