package com.ciesto.controller;

import com.ciesto.model.CompanyPromoter;
import com.ciesto.service.CompanyPromoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/promoters")
public class CompanyPromoterController {

    @Autowired
    private CompanyPromoterService promoterService;

    @GetMapping("/list")
    public ResponseEntity<List<CompanyPromoter>> getPromoters(
            @RequestParam(required = false) String companyName,
            @RequestParam(required = false) String promoterName,
            @RequestParam(required = false) String pan,
            @RequestParam(required = false) String designation
    ) {
        List<CompanyPromoter> promoters = promoterService.filterPromoters(companyName, promoterName, pan, designation);
        return ResponseEntity.ok(promoters);
    }

    @GetMapping("/company/{companyId}")
    public List<CompanyPromoter> getPromotersByCompanyId(@PathVariable Long companyId) {
        return promoterService.getPromotersByCompanyId(companyId);
    }
}
