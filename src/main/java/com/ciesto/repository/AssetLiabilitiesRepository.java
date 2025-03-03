package com.ciesto.repository;

import com.ciesto.model.AssetLiabilities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AssetLiabilitiesRepository extends JpaRepository<AssetLiabilities, Long>, JpaSpecificationExecutor<AssetLiabilities> {
}
