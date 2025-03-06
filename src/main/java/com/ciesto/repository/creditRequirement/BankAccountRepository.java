package com.ciesto.repository.creditRequirement;

import com.ciesto.model.creditRequirement.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    List<BankAccount> findByCreditContext_Id(Long creditContextId);
}

