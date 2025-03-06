package com.ciesto.service.creditApplication;

import com.ciesto.common.customException.ResourceNotFoundException;
import com.ciesto.model.creditApplication.CollateralSecurity;
import com.ciesto.model.creditApplication.CreditApplication;
import com.ciesto.repository.creditApplication.CollateralSecurityRepository;
import com.ciesto.repository.creditApplication.CreditApplicationRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CollateralSecurityService {
    private final CollateralSecurityRepository repository;

    private final CreditApplicationRepository creditApplicationRepository;

    public CollateralSecurityService(CollateralSecurityRepository repository,
                                     CreditApplicationRepository creditApplicationRepository) {
        this.repository = repository;
        this.creditApplicationRepository=creditApplicationRepository;
    }

    @Transactional
    public CollateralSecurity save(Long creditApplicationId, CollateralSecurity entity) {
        CreditApplication creditApplication = creditApplicationRepository.findById(creditApplicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Credit Application not found", 404L));
        entity.setCreditApplication(creditApplication);
        return repository.save(entity);
    }

    public CollateralSecurity getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CollateralSecurity not found with id: " + id, 404L));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("CollateralSecurity not found with id: " + id, 404L);
        }
        repository.deleteById(id);
    }

    public Page<CollateralSecurity> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
