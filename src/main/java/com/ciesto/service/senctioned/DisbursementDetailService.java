package com.ciesto.service.senctioned;

import com.ciesto.common.customException.ResourceNotFoundException;
import com.ciesto.model.senctioned.DisbursementDetail;
import com.ciesto.model.senctioned.SanctionedLoan;
import com.ciesto.repository.senctioned.DisbursementDetailRepository;
import com.ciesto.repository.senctioned.SanctionedLoanRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DisbursementDetailService {

    private final DisbursementDetailRepository disbursementDetailRepository;
    private final SanctionedLoanRepository sanctionedLoanRepository;

    @Transactional
    public DisbursementDetail createDisbursementDetail(Long loanId, DisbursementDetail detail) {
        SanctionedLoan loan = sanctionedLoanRepository.findById(loanId)
                .orElseThrow(() -> new ResourceNotFoundException("Sanctioned Loan not found", 404L));
        detail.setSanctionedLoan(loan);
        return disbursementDetailRepository.save(detail);
    }

    public Page<DisbursementDetail> getFilteredDisbursements(Specification<DisbursementDetail> spec, Pageable pageable) {
        return disbursementDetailRepository.findAll(spec, pageable);
    }

    public List<DisbursementDetail> getDisbursementDetailsByLoanId(Long loanId) {
        SanctionedLoan loan = sanctionedLoanRepository.findById(loanId)
                .orElseThrow(() -> new ResourceNotFoundException("Sanctioned Loan not found with ID: " + loanId, 404L));

        return disbursementDetailRepository.findBySanctionedLoan(loan);
    }

}
