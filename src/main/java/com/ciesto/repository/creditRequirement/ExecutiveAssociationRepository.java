package com.ciesto.repository.creditRequirement;

import com.ciesto.model.creditRequirement.ExecutiveAssociation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExecutiveAssociationRepository extends JpaRepository<ExecutiveAssociation, Long> {
}
