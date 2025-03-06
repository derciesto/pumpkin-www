package com.ciesto.controller.senctioned;

import com.ciesto.dto.ApiResponse;
import com.ciesto.model.senctioned.StatementDownload;
import com.ciesto.service.senctioned.StatementDownloadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/statements")
@RequiredArgsConstructor
public class StatementDownloadController {

    private final StatementDownloadService service;

    @PostMapping
    public ResponseEntity<ApiResponse<StatementDownload>> createStatement(
            @RequestParam Long sanctionedLoanId,
            @RequestBody StatementDownload statement) {
        try {
            StatementDownload createdStatement = service.createStatement(sanctionedLoanId, statement);
            return ResponseEntity.ok(ApiResponse.success(createdStatement));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error creating statement: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StatementDownload>> getStatement(@PathVariable Long id) {
        try {
            Optional<StatementDownload> statementById = service.getStatementById(id);
            if (statementById.isPresent()) {
                return ResponseEntity.ok(ApiResponse.success(statementById.get()));
            } else {
                return ResponseEntity.accepted().body(ApiResponse.error("No statement found for ID: " + id));
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error fetching statement: " + e.getMessage()));
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<ApiResponse<List<StatementDownload>>> filterStatements(
            @RequestParam(required = false) String documentType,
            @RequestParam(required = false) Integer financialYear,
            @RequestParam(required = false) String fromDate,
            @RequestParam(required = false) String toDate) {
        try {
            List<StatementDownload> filteredStatements = service.filterStatements(documentType, financialYear, fromDate, toDate);
            return ResponseEntity.ok(ApiResponse.success(filteredStatements));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error("Error filtering statements: " + e.getMessage()));
        }
    }
}
