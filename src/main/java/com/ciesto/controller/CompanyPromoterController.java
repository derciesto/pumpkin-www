package com.ciesto.controller;

import com.ciesto.model.CompanyPromoter;
import com.ciesto.repository.CompanyProfileRepository;
import com.ciesto.service.CompanyPromoterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/promoters")
public class CompanyPromoterController {

    private static final Logger logger = LogManager.getLogger(CompanyPromoterController.class);


    @Autowired
    private CompanyProfileRepository companyProfileRepository;

    @Autowired
    private CompanyPromoterService promoterService;

    @GetMapping("/list")
    public ResponseEntity<List<CompanyPromoter>> getPromoters(
            @RequestParam(required = false) String companyName,
            @RequestParam(required = false) String promoterName,
            @RequestParam(required = false) String pan,
            @RequestParam(required = false) String designation
    ) {
        try {

            List<CompanyPromoter> promoters = promoterService.filterPromoters(companyName, promoterName, pan, designation);
            return ResponseEntity.ok(promoters);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ArrayList<>());
        }
    }

    @GetMapping("/company/{companyId}")
    public List<CompanyPromoter> getPromotersByCompanyId(@PathVariable Long companyId) {
        return promoterService.getPromotersByCompanyId(companyId);
    }

    @PostMapping
    public ResponseEntity<CompanyPromoter> createPromoter(
            @RequestParam Long companyId,
            @RequestBody CompanyPromoter promoter
    ) {
        return companyProfileRepository.findById(companyId).map(company -> {
            promoter.setCompany(company);

            // Link social references
            if (promoter.getSocialReferences() != null) {
                promoter.getSocialReferences().forEach(ref -> ref.setPromoter(promoter));
            }

            // Link executive associations
            if (promoter.getExecutiveAssociations() != null) {
                promoter.getExecutiveAssociations().forEach(assoc -> assoc.setPromoter(promoter));
            }

            CompanyPromoter savedPromoter = promoterService.save(promoter);
            return new ResponseEntity<>(savedPromoter, HttpStatus.CREATED);
        }).orElseGet(() -> ResponseEntity.badRequest().build());
    }

}
