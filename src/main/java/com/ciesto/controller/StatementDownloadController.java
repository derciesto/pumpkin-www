package com.ciesto.controller;

import com.ciesto.model.StatementDownload;
import com.ciesto.repository.StatementDownloadRepository;
import com.ciesto.service.StatementDownloadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/statements")
@RequiredArgsConstructor
public class StatementDownloadController {
    private final StatementDownloadService service;

    @PostMapping
    public StatementDownload createStatement(@RequestParam Long sanctionedLoanId, @RequestBody StatementDownload statement) {
        return service.createStatement(sanctionedLoanId, statement);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStatement(@PathVariable Long id) {
        Optional<StatementDownload> statementById = service.getStatementById(id);
        if(statementById.isPresent()) {
            return ResponseEntity.ok(statementById.get());
        }
        else {
            return ResponseEntity.accepted().body("No statement Found");
        }
    }


    @GetMapping("/filter")
    public List<StatementDownload> filterStatements(
            @RequestParam(required = false) String documentType,
            @RequestParam(required = false) Integer financialYear,
            @RequestParam(required = false) String fromDate,
            @RequestParam(required = false) String toDate) {
        return service.filterStatements(documentType, financialYear, fromDate, toDate);
    }
}

