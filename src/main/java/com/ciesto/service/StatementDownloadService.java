package com.ciesto.service;

import com.ciesto.model.SanctionedLoan;
import com.ciesto.model.StatementDownload;
import com.ciesto.repository.SanctionedLoanRepository;
import com.ciesto.repository.StatementDownloadRepository;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StatementDownloadService {

    @Autowired
    private StatementDownloadRepository statementDownloadRepository;

    @Autowired
    private SanctionedLoanRepository sanctionedLoanRepository;


    public StatementDownload createStatement(Long sanctionedLoanId, StatementDownload statement) {
        // Fetch the sanctioned loan
        SanctionedLoan sanctionedLoan = sanctionedLoanRepository.findById(sanctionedLoanId)
                .orElseThrow(() -> new RuntimeException("Sanctioned Loan not found"));

        // Set the loan before saving
        statement.setSanctionedLoan(sanctionedLoan);
        return statementDownloadRepository.save(statement);
    }

    public Optional<StatementDownload> getStatementById(Long id) {
        return statementDownloadRepository.findById(id);
    }

    @Transactional
    public StatementDownload updateStatementDownload(Long id, StatementDownload updatedStatement) {
        return statementDownloadRepository.findById(id).map(statement -> {
            statement.setDateAvailable(updatedStatement.getDateAvailable());
            statement.setDocument(updatedStatement.getDocument());
            statement.setDescription(updatedStatement.getDescription());
            statement.setOwner(updatedStatement.getOwner());
            return statementDownloadRepository.save(statement);
        }).orElseThrow(() -> new RuntimeException("StatementDownload not found"));
    }

    @Transactional
    public void deleteStatementDownload(Long id) {
        statementDownloadRepository.deleteById(id);
    }

    public List<StatementDownload> filterStatements(String documentType, Integer financialYear, String fromDate, String toDate) {
        Specification<StatementDownload> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (documentType != null && !documentType.isEmpty()) {
                predicates.add(cb.equal(root.get("document"), documentType));
            }

            if (financialYear != null) {
                LocalDate startOfYear = LocalDate.of(financialYear, 1, 1);
                LocalDate endOfYear = LocalDate.of(financialYear, 12, 31);
                predicates.add(cb.between(root.get("dateAvailable"), startOfYear, endOfYear));
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            if (fromDate != null && !fromDate.isEmpty() && toDate != null && !toDate.isEmpty()) {
                LocalDate from = LocalDate.parse(fromDate, formatter);
                LocalDate to = LocalDate.parse(toDate, formatter);
                predicates.add(cb.between(root.get("dateAvailable"), from, to));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return statementDownloadRepository.findAll((Sort) spec);
    }
}