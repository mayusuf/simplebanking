package com.secured.banking.feature.account.controller;

import com.secured.banking.entities.Account;
import com.secured.banking.feature.account.dto.AccountDTO;
import com.secured.banking.feature.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.secured.banking.utility.route.RouteInformation.ACCOUNTS_ROUTE;

@RestController
@RequestMapping(ACCOUNTS_ROUTE)
public class AccountController {
    @Autowired
    private AccountService accountService;
    @GetMapping("")
    public ResponseEntity<List<AccountDTO>> getAccounts(){
        return ResponseEntity.ok()
                .header("Content-Type","application/json")
                .body(accountService.getAccounts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccount(@PathVariable int id){
        return ResponseEntity
                .ok()
                .header("Content-Type","application/json")
                .body(accountService.getAccount(id));
    }

    @PostMapping("")
    public ResponseEntity<AccountDTO> addAccount(@RequestBody AccountDTO accountDto){

        return ResponseEntity
                .ok()
                .header("Content-Type","application/json")
                .body(accountService.addAccount(accountDto));
    }

    @PutMapping("")
    public ResponseEntity<AccountDTO> updateAccount(@RequestBody AccountDTO account){
        return ResponseEntity
                .ok()
                .header("Content-Type","application/json")
                .body(accountService.updateAccount(account));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteAccount(@PathVariable int id){
        return ResponseEntity
                .ok()
                .header("Content-Type","application/json")
                .body(accountService.deleteAccount(id));
    }
}
