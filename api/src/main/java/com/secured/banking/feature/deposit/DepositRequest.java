package com.secured.banking.feature.deposit;

import com.secured.banking.entities.Account;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepositRequest {
    private double Amount;
    private String Description;


    private String account_id;
}
