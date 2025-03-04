package com.ciesto.service;

import com.ciesto.common.Constants;
import com.ciesto.common.customException.ResourceNotFoundException;
import com.ciesto.model.CompanyProfile;
import com.ciesto.model.CompanyPromoter;
import com.ciesto.repository.CompanyProfileRepository;
import com.ciesto.repository.CompanyPromoterRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyPromoterService {

    private static final Logger logger = LogManager.getLogger(CompanyPromoterService.class);

    @Autowired
    private CompanyPromoterRepository promoterRepository;

    @Autowired
    private CompanyProfileRepository companyProfileRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<CompanyPromoter> getAllPromoters() {
        logger.info("Fetching all promoters");
        return promoterRepository.findAll();
    }

    public List<CompanyPromoter> getPromotersByCompanyId(Long companyId) {
        return promoterRepository.findByCompanyId(companyId);
    }

    public List<CompanyPromoter> filterPromoters(String companyName, String promoterName, String pan, String designation) {
        logger.info("Filtering promoters based on companyName={}, promoterName={}, pan={}, designation={}", companyName, promoterName, pan, designation);

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CompanyPromoter> query = cb.createQuery(CompanyPromoter.class);
        Root<CompanyPromoter> root = query.from(CompanyPromoter.class);
        Join<Object, Object> companyJoin = root.join(Constants.COMPANY);

        List<Predicate> predicates = new ArrayList<>();

        if (companyName != null && !companyName.isEmpty()) {
            predicates.add(cb.like(cb.lower(companyJoin.get("name")), "%" + companyName.toLowerCase() + "%"));
        }
        if (promoterName != null && !promoterName.isEmpty()) {
            predicates.add(cb.like(cb.lower(root.get("name")), "%" + promoterName.toLowerCase() + "%"));
        }
        if (pan != null && !pan.isEmpty()) {
            predicates.add(cb.equal(root.get("pan"), pan));
        }
        if (designation != null && !designation.isEmpty()) {
            predicates.add(cb.like(cb.lower(root.get("designation")), Constants.PERCENT_SYMBOL + designation.toLowerCase() + "%"));
        }

        query.select(root).where(predicates.toArray(new Predicate[0]));

        List<CompanyPromoter> results = entityManager.createQuery(query).getResultList();
        logger.info("Filtered promoters count: {}", results.size());

        return results;
    }

    public CompanyPromoter save(CompanyPromoter promoter) {
        return promoterRepository.save(promoter);
    }

    public void deletePromoter(Long id) {
        logger.info("Deleting promoter with ID: {}", id);
        promoterRepository.deleteById(id);
    }
}
