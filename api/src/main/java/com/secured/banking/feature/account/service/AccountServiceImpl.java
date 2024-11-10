package com.secured.banking.feature.account.service;

import com.secured.banking.entities.Account;
import com.secured.banking.entities.Address;
import com.secured.banking.feature.account.dto.AccountDTO;
import com.secured.banking.feature.account.repository.AccountRepository;
import com.secured.banking.feature.account.repository.AddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<AccountDTO> getAccounts() {
        List<Account> accounts = accountRepository.findAll();

        List<AccountDTO> accountDTOList = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();

        for (Account account : accounts) {
            AccountDTO dto = new AccountDTO();
            modelMapper.map(account, dto);

            Address dbAddress = addressRepository.findByAccountId(account.getId());
            if (dbAddress != null) {
                dto.setStreet(dbAddress.getStreet());
                dto.setCity(dbAddress.getCity());
                dto.setState(dbAddress.getState());
                dto.setZip(dbAddress.getZip());
            }
            accountDTOList.add(dto);
        }

        return accountDTOList;
    }

    @Override
    public AccountDTO getAccount(int id) {
        Account account = accountRepository.findById(id).get();
        ModelMapper modelMapper = new ModelMapper();
        AccountDTO dto = new AccountDTO();
        modelMapper.map(account, dto);
        return dto;
    }

    @Override
    public AccountDTO addAccount(AccountDTO accountDto) {
        Account newAccount = new Account();
        newAccount.setAccountNo(accountDto.getAccountNo());
        newAccount.setName(accountDto.getName());
        newAccount.setEmail(accountDto.getEmail());
        newAccount.setAccountType(accountDto.getAccountType());
        newAccount.setBalance(accountDto.getBalance());

        newAccount = accountRepository.save(newAccount);

        Address newAddress = new Address();
        newAddress.setAccount(newAccount);
        newAddress.setStreet(accountDto.getStreet());
        newAddress.setCity(accountDto.getCity());
        newAddress.setState(accountDto.getState());
        newAddress.setZip(accountDto.getZip());

        newAddress = addressRepository.save(newAddress);

        newAccount.setAddress(newAddress);

        accountDto.setId(newAccount.getId());
        return accountDto;
    }

    @Override
    public AccountDTO updateAccount(AccountDTO account) {
        Account dbAccount = accountRepository.findById(account.getId()).get();
        if (dbAccount != null) {
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.map(account, dbAccount);
            dbAccount = accountRepository.save(dbAccount);

            modelMapper.map(dbAccount, account);
        }


        return account;
    }

    @Override
    public boolean deleteAccount(int id) {
        try {
            accountRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
