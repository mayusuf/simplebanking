package com.secured.banking.feature.withdraw.service;

import com.secured.banking.entities.AccountEntry;
import com.secured.banking.feature.withdraw.payload.AccountEntryDTO;
import com.secured.banking.feature.withdraw.payload.WithdrawRequest;

import java.util.List;

public interface WithdrawService {

    AccountEntry withdraw(WithdrawRequest withdrawRequest);

    List<AccountEntryDTO> getWithdraws(int id);
}
