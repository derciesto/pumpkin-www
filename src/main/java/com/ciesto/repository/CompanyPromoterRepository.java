package com.ciesto.repository;

import com.ciesto.model.CompanyPromoter;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CompanyPromoterRepository extends JpaRepository<CompanyPromoter, Long> {
    List<CompanyPromoter> findByCompanyId(Long companyId);

}
