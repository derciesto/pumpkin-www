package com.ciesto.repository;

import com.ciesto.model.CollateralSecurity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollateralSecurityRepository extends JpaRepository<CollateralSecurity, Long> {
}
