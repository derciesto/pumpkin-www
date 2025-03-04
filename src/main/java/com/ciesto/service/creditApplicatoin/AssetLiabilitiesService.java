package com.ciesto.service.creditApplicatoin;

import com.ciesto.model.AssetLiabilities;
import com.ciesto.model.CreditApplication;
import com.ciesto.repository.AssetLiabilitiesRepository;
import com.ciesto.common.customException.ResourceNotFoundException;
import com.ciesto.repository.CreditApplicationRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AssetLiabilitiesService {
    private final AssetLiabilitiesRepository repository;
    private final CreditApplicationRepository creditApplicationRepository;

    public AssetLiabilitiesService(AssetLiabilitiesRepository repository,
                                   CreditApplicationRepository creditApplicationRepository) {
        this.repository = repository;
        this.creditApplicationRepository=creditApplicationRepository;
    }

    @Transactional
    public AssetLiabilities save(Long creditApplicationId, AssetLiabilities entity) {
        CreditApplication creditApplication = creditApplicationRepository.findById(creditApplicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Credit Application not found", 404L));
        entity.setCreditApplication(creditApplication);
        return repository.save(entity);
    }

    public AssetLiabilities getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("AssetLiabilities not found", 404L));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Page<AssetLiabilities> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
