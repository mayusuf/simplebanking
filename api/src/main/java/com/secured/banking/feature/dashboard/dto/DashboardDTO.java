package com.secured.banking.feature.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardDTO {
    private int SavingAccountCount;
    private int CheckingAccountCount;
    private double TotalDeposits;
    private double TotalWithdraws;
}
