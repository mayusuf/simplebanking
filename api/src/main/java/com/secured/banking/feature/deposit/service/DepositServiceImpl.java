package com.secured.banking.feature.deposit.service;

import com.secured.banking.entities.Account;
import com.secured.banking.entities.AccountEntry;
import com.secured.banking.feature.account.repository.AccountRepository;
import com.secured.banking.feature.deposit.DepositRequest;
import com.secured.banking.feature.deposit.repository.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DepositServiceImpl implements DepositService {

    @Autowired
    private DepositRepository depositRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public AccountEntry deposit(DepositRequest depositRequest) {

        Account account = accountRepository.findById(Integer.parseInt(depositRequest.getAccount_id())).get();

        AccountEntry accountEntry = new AccountEntry();
        accountEntry.setAmount(depositRequest.getAmount());
        accountEntry.setAccount(account);
        accountEntry.setDescription(depositRequest.getDescription());
        accountEntry.setDate(LocalDate.now());
        accountEntry.setTransactionType("deposit");

        accountEntry = depositRepository.save(accountEntry);


        account.setBalance(account.getBalance() + depositRequest.getAmount());
        accountRepository.save(account);

        return accountEntry;
    }
}