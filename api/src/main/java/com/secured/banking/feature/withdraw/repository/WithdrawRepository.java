package com.secured.banking.feature.withdraw.repository;

import com.secured.banking.entities.AccountEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WithdrawRepository extends JpaRepository<AccountEntry, Integer> {

    @Query("select a from AccountEntry a where a.Account.Id = ?1 and a.TransactionType='withdraw'")
    List<AccountEntry> getWithdraws(int id);
}
