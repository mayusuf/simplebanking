package com.secured.banking.feature.withdraw.payload;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AccountEntryDTO {
    private int Id;
    private String TransactionType; //deposit or withdraw
    private LocalDate Date;
    private double Amount;
    private String Description;
}
