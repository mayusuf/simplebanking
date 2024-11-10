package com.secured.banking.feature.account.service;

import com.secured.banking.entities.Account;
import com.secured.banking.feature.account.dto.AccountDTO;

import java.util.List;

public interface AccountService {
    List<AccountDTO> getAccounts();

    AccountDTO getAccount(int id);

    AccountDTO addAccount(AccountDTO accountDto);

    AccountDTO updateAccount(AccountDTO book);

    boolean deleteAccount(int id);
}
