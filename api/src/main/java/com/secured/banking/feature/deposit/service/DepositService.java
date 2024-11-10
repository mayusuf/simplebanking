package com.secured.banking.feature.deposit.service;

import com.secured.banking.entities.AccountEntry;
import com.secured.banking.feature.deposit.DepositRequest;

public interface DepositService {
    AccountEntry deposit(DepositRequest depositRequest);
}