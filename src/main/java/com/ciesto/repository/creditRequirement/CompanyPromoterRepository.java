package com.ciesto.repository.creditRequirement;

import com.ciesto.model.creditRequirement.CompanyPromoter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CompanyPromoterRepository extends JpaRepository<CompanyPromoter, Long>, JpaSpecificationExecutor<CompanyPromoter> {
    List<CompanyPromoter> findByCompanyId(Long companyId);

}
