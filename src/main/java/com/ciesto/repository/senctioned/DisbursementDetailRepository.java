package com.ciesto.repository.senctioned;

import com.ciesto.model.senctioned.DisbursementDetail;
import com.ciesto.model.senctioned.SanctionedLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisbursementDetailRepository extends JpaRepository<DisbursementDetail, Long>, JpaSpecificationExecutor<DisbursementDetail> {
    List<DisbursementDetail> findBySanctionedLoan(SanctionedLoan sanctionedLoan);

}
