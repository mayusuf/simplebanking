package com.secured.banking.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private Integer AccountNo;
    private String Name;
    private String Email;
    private String AccountType;
    private Double Balance;

    @OneToMany(mappedBy = "Account")
    private List<AccountEntry> AccountEntries;

    @OneToOne(mappedBy = "Account")
    private Address Address;
}
