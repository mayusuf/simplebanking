package com.secured.banking.feature.withdraw.service;

import com.secured.banking.entities.Account;
import com.secured.banking.entities.AccountEntry;
import com.secured.banking.exception.CommonException;
import com.secured.banking.feature.account.repository.AccountRepository;
import com.secured.banking.feature.withdraw.payload.AccountEntryDTO;
import com.secured.banking.feature.withdraw.payload.WithdrawRequest;
import com.secured.banking.feature.withdraw.repository.WithdrawRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class WithdrawServiceImpl implements WithdrawService {

    @Autowired
    private WithdrawRepository withdrawRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public AccountEntry withdraw(WithdrawRequest withdrawRequest) {

        Account account = accountRepository.findById(Integer.parseInt(withdrawRequest.getAccount_id())).get();

        if (account == null) {
            throw new CommonException("account not found");
        }
        if (account.getBalance() < withdrawRequest.getAmount()) {
            throw new CommonException("insufficient balance");
        }

        AccountEntry accountEntry = new AccountEntry();
        accountEntry.setAmount(withdrawRequest.getAmount());
        accountEntry.setDescription(withdrawRequest.getDescription());
        accountEntry.setDate(LocalDate.now());
        accountEntry.setAccount(account);
        accountEntry.setTransactionType("withdraw");

        accountEntry = withdrawRepository.save(accountEntry);

        account.setBalance(account.getBalance() - withdrawRequest.getAmount());
        accountRepository.save(account);

        return accountEntry;

    }

    @Override
    public List<AccountEntryDTO> getWithdraws(int id) {
        List<AccountEntry> withdraws = withdrawRepository.getWithdraws(id);

        List<AccountEntryDTO> withdrawEntries = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for (AccountEntry withdraw : withdraws){
            withdrawEntries.add(modelMapper.map(withdraw, AccountEntryDTO.class));
        }
        return withdrawEntries;
    }
}
