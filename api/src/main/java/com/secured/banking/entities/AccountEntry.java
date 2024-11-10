package com.secured.banking.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class AccountEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String TransactionType; //deposit or withdraw
    private LocalDate Date;
    private double Amount;
    private String Description;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account Account;

//    // Getter for the account_id
//    public int getAccountId() {
//        return Account != null ? Account.getId() : null;
//    }
}
