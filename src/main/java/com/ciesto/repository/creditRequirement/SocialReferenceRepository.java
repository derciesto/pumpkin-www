package com.ciesto.repository.creditRequirement;

import com.ciesto.model.creditRequirement.SocialReference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialReferenceRepository extends JpaRepository<SocialReference, Long> {
}
