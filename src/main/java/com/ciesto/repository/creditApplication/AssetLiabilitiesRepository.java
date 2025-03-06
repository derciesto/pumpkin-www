package com.ciesto.repository.creditApplication;

import com.ciesto.model.creditApplication.AssetLiabilities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetLiabilitiesRepository extends JpaRepository<AssetLiabilities, Long>, JpaSpecificationExecutor<AssetLiabilities> {
}
