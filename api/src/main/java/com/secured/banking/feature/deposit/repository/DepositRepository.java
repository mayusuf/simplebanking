package com.secured.banking.feature.deposit.repository;

import com.secured.banking.entities.AccountEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositRepository extends JpaRepository<AccountEntry, Integer> {

}
