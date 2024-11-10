package com.secured.banking.feature.account.dto;

import lombok.Data;

@Data
public class AccountDTO {
    private Integer Id;
    private Integer AccountNo;
    private String Name;
    private String Email;
    private String AccountType;
    private Double Balance;

    private String Street;
    private String City;
    private String State;
    private Integer Zip;
}
