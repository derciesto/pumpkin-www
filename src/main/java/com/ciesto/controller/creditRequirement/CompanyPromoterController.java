package com.ciesto.controller.creditRequirement;

import com.ciesto.dto.ApiResponse;
import com.ciesto.model.creditRequirement.CompanyPromoter;
import com.ciesto.repository.creditRequirement.CompanyProfileRepository;
import com.ciesto.service.creditRequest.CompanyPromoterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/promoters")
public class CompanyPromoterController {

    private static final Logger logger = LogManager.getLogger(CompanyPromoterController.class);

    private final CompanyProfileRepository companyProfileRepository;
    private final CompanyPromoterService promoterService;

    public CompanyPromoterController(CompanyProfileRepository companyProfileRepository, CompanyPromoterService promoterService) {
        this.companyProfileRepository = companyProfileRepository;
        this.promoterService = promoterService;
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<CompanyPromoter>>> getPromoters(
            @RequestParam(required = false) String companyName,
            @RequestParam(required = false) String promoterName,
            @RequestParam(required = false) String pan,
            @RequestParam(required = false) String designation
    ) {
        try {
            List<CompanyPromoter> promoters = promoterService.filterPromoters(companyName, promoterName, pan, designation);
            return ResponseEntity.ok(ApiResponse.success(promoters));
        } catch (Exception e) {
            logger.error("Error fetching promoters", e);
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error fetching promoters: " + e.getMessage()));
        }
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<ApiResponse<List<CompanyPromoter>>> getPromotersByCompanyId(@PathVariable Long companyId) {
        try {
            List<CompanyPromoter> promoters = promoterService.getPromotersByCompanyId(companyId);
            return ResponseEntity.ok(ApiResponse.success(promoters));
        } catch (Exception e) {
            logger.error("Error fetching promoters by company ID", e);
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error fetching promoters: " + e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CompanyPromoter>> createPromoter(
            @RequestParam Long companyId,
            @RequestBody CompanyPromoter promoter
    ) {
        try {
            Optional<CompanyPromoter> savedPromoter = companyProfileRepository.findById(companyId).map(company -> {
                promoter.setCompany(company);

                if (promoter.getSocialReferences() != null) {
                    promoter.getSocialReferences().forEach(ref -> ref.setPromoter(promoter));
                }

                if (promoter.getExecutiveAssociations() != null) {
                    promoter.getExecutiveAssociations().forEach(assoc -> assoc.setPromoter(promoter));
                }

                return promoterService.save(promoter);
            });

            return savedPromoter
                    .map(promoterObj -> ResponseEntity.ok(ApiResponse.success(promoterObj)))
                    .orElseGet(() -> ResponseEntity.badRequest().body(ApiResponse.error("Invalid company ID")));

        } catch (Exception e) {
            logger.error("Error creating promoter", e);
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error creating promoter: " + e.getMessage()));
        }
    }
}
