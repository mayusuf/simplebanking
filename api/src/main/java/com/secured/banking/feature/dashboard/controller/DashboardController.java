package com.secured.banking.feature.dashboard.controller;

import com.secured.banking.feature.account.dto.AccountDTO;
import com.secured.banking.feature.account.service.AccountService;
import com.secured.banking.feature.dashboard.dto.DashboardDTO;
import com.secured.banking.feature.dashboard.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.secured.banking.utility.route.RouteInformation.ACCOUNTS_ROUTE;
import static com.secured.banking.utility.route.RouteInformation.DASHBOARD_ROUTE;

@RestController
@RequestMapping(DASHBOARD_ROUTE)
public class DashboardController {
    @Autowired
    private DashboardService dashboardService;
    @GetMapping("")
    public ResponseEntity<DashboardDTO> getDashboardData(){
        return ResponseEntity.ok()
                .header("Content-Type","application/json")
                .body(dashboardService.getDashboardData());
    }

}
