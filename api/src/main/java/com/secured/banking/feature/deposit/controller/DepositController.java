package com.secured.banking.feature.deposit.controller;

import com.secured.banking.feature.account.dto.AccountDTO;
import com.secured.banking.feature.account.service.AccountService;
import com.secured.banking.feature.deposit.DepositRequest;
import com.secured.banking.feature.deposit.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.secured.banking.utility.route.RouteInformation.DEPOSIT_ROUTE;

@RestController
@RequestMapping(DEPOSIT_ROUTE)
public class DepositController {
    @Autowired
    private DepositService depositService;
    @Autowired
    private AccountService accountService;

    @PostMapping("")
    public ResponseEntity<Integer> deposit(@RequestBody DepositRequest depositRequest) {

        AccountDTO account = accountService.getAccount(Integer.parseInt(depositRequest.getAccount_id()));

        if (account == null) {
            // Handle case where account does not exist
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);  // Or return an error response with details
        }

        return ResponseEntity
                .ok()
//                .header("Content-Type", "application/json")
                .body(depositService.deposit(depositRequest).getId());
    }
}