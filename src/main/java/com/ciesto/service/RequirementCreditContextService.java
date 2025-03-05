package com.ciesto.service;

import com.ciesto.model.CreditRequirement;
import com.ciesto.model.RequirementCreditContext;
import com.ciesto.repository.CreditRequirementRepository;
import com.ciesto.repository.RequirementCreditContextRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class RequirementCreditContextService {
    private final RequirementCreditContextRepository repository;
    private final CreditRequirementRepository creditRequirementRepository;

    private static final String UPLOAD_DIR = "/uploads/documents/";

    public RequirementCreditContext createCreditContext(Long creditRequirementId, RequirementCreditContext creditContext,
                                                        MultipartFile cancelledCheck, MultipartFile bankAccountStatement) {
        CreditRequirement creditRequirement = creditRequirementRepository.findById(creditRequirementId)
                .orElseThrow(() -> new RuntimeException("Credit Requirement not found"));

        creditContext.setCreditRequirement(creditRequirement);

        // Save documents
//        creditContext.setCancelledCheck(saveFile(cancelledCheck));
//        creditContext.setBankAccountStatement(saveFile(bankAccountStatement));

        return repository.save(creditContext);
    }

    private String saveFile(MultipartFile file) {
        if (file == null || file.isEmpty()) return null;
        try {
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String filePath = UPLOAD_DIR + file.getOriginalFilename();
            Files.write(Paths.get(filePath), file.getBytes());
            return filePath;
        } catch (Exception e) {
            throw new RuntimeException("File upload failed", e);
        }
    }

    public RequirementCreditContext getByCreditRequirement(Long creditRequirementId) {
        return repository.findByCreditRequirement_Id(creditRequirementId)
                .orElseThrow(() -> new RuntimeException("Requirement Credit Context not found"));
    }
}

