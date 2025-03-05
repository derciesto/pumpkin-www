package com.ciesto.service;

import com.ciesto.model.BankAccount;
import com.ciesto.model.RequirementCreditContext;
import com.ciesto.repository.BankAccountRepository;
import com.ciesto.repository.RequirementCreditContextRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BankAccountService {
    private final BankAccountRepository repository;
    private final RequirementCreditContextRepository creditContextRepository;

    public BankAccount createBankAccount(Long creditContextId, BankAccount bankAccount) {
        RequirementCreditContext creditContext = creditContextRepository.findById(creditContextId)
                .orElseThrow(() -> new RuntimeException("Credit Context not found"));
        bankAccount.setCreditContext(creditContext);
        return repository.save(bankAccount);
    }

    public List<BankAccount> getByCreditContext(Long creditContextId) {
        return repository.findByCreditContext_Id(creditContextId);
    }

    public BankAccount updateBankAccount(Long id, BankAccount updatedBankAccount) {
        return repository.findById(id).map(existing -> {
            existing.setAccountNumber(updatedBankAccount.getAccountNumber());
            existing.setBankName(updatedBankAccount.getBankName());
            existing.setIfscCode(updatedBankAccount.getIfscCode());
            existing.setAccountHolderName(updatedBankAccount.getAccountHolderName());
            existing.setAccountType(updatedBankAccount.getAccountType());
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Bank Account not found"));
    }

    public void deleteBankAccount(Long id) {
        repository.deleteById(id);
    }
}

