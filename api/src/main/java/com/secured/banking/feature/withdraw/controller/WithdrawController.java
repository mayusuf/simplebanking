package com.secured.banking.feature.withdraw.controller;

import com.secured.banking.entities.AccountEntry;
import com.secured.banking.feature.withdraw.payload.AccountEntryDTO;
import com.secured.banking.feature.withdraw.payload.WithdrawRequest;
import com.secured.banking.feature.withdraw.service.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.secured.banking.utility.route.RouteInformation.WITHDRAW_ROUTE;

@RestController
@RequestMapping(WITHDRAW_ROUTE)
public class WithdrawController {
    @Autowired
    private WithdrawService withdrawService;

    @GetMapping("/{id}")
    public ResponseEntity<List<AccountEntryDTO>>withdraws(@PathVariable int id){

        return ResponseEntity
                .ok()
//                .header("Content-Type", "application/json")
                .body(withdrawService.getWithdraws(id));

    }
    @PostMapping("")
    public ResponseEntity<Integer> withdraw(@RequestBody WithdrawRequest withdrawRequest) {

        return ResponseEntity
                .ok()
//                .header("Content-Type", "application/json")
                .body(withdrawService.withdraw(withdrawRequest).getId());

    }
}
