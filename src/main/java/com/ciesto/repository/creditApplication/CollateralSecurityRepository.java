package com.ciesto.repository.creditApplication;

import com.ciesto.model.creditApplication.CollateralSecurity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollateralSecurityRepository extends JpaRepository<CollateralSecurity, Long> {
}
