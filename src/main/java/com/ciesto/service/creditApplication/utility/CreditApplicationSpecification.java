package com.ciesto.service.creditApplication.utility;

import com.ciesto.model.creditApplication.CreditApplication;
import org.springframework.data.jpa.domain.Specification;

public class CreditApplicationSpecification {

    public static Specification<CreditApplication> withFilters(String companyName, String purpose, String identifiedOn, String sourceChannel, String status) {
        return (root, query, cb) -> {
            Specification<CreditApplication> spec = Specification.where(null);

            if (companyName != null) {
                spec = spec.and((root1, query1, cb1) -> cb1.equal(root1.get("company").get("name"), companyName.replace(" ","")));
            }
            if (purpose != null) {
                spec = spec.and((root1, query1, cb1) -> cb1.like(root1.get("purpose"), "%" + purpose + "%"));
            }
            if (identifiedOn != null) {
                spec = spec.and((root1, query1, cb1) -> cb1.equal(root1.get("identifiedOn"), identifiedOn));
            }
            if (sourceChannel != null) {
                spec = spec.and((root1, query1, cb1) -> cb1.equal(root1.get("sourceChannel"), sourceChannel));
            }
            if (status != null) {
                spec = spec.and((root1, query1, cb1) -> cb1.equal(root1.get("status"), status));
            }

            return spec.toPredicate(root, query, cb);
        };
    }
}
