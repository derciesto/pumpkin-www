package com.ciesto.repository;

import com.ciesto.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    List<BankAccount> findByCreditContext_Id(Long creditContextId);
}

