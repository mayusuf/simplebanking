package com.secured.banking.feature.user.payload;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    private String username;
    private String email;
    private String password;
}
