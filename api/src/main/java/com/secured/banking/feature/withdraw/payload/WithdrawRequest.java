package com.secured.banking.feature.withdraw.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawRequest {
    private double Amount;
    private String Description;
    private String account_id;
}
