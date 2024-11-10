package com.secured.banking.feature.dashboard.service;

import com.secured.banking.entities.Account;
import com.secured.banking.entities.AccountEntry;
import com.secured.banking.entities.Address;
import com.secured.banking.feature.account.dto.AccountDTO;
import com.secured.banking.feature.account.repository.AccountRepository;
import com.secured.banking.feature.account.repository.AddressRepository;
import com.secured.banking.feature.dashboard.dto.DashboardDTO;
import com.secured.banking.feature.deposit.repository.DepositRepository;
import com.secured.banking.feature.withdraw.repository.WithdrawRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private WithdrawRepository withdrawRepository;

    @Autowired
    private DepositRepository depositRepository;


    @Override
    public DashboardDTO getDashboardData() {
        List<Account> accounts = accountRepository.findAll();
        DashboardDTO dashboardData = new DashboardDTO();

        for (Account a : accounts) {
            if (a.getAccountType().equals("Savings")) {
                dashboardData.setSavingAccountCount(dashboardData.getSavingAccountCount() + 1);
            } else {
                dashboardData.setCheckingAccountCount(dashboardData.getCheckingAccountCount() + 1);
            }
        }

        var withdraws = withdrawRepository.findAll();
        for (AccountEntry accountEntry: withdraws){
            if (accountEntry.getTransactionType().equals("withdraw")){

                dashboardData.setTotalWithdraws(dashboardData.getTotalWithdraws() + accountEntry.getAmount());
            } else {

                dashboardData.setTotalDeposits(dashboardData.getTotalDeposits() + accountEntry.getAmount());
            }
        }


        return dashboardData;
    }
}
