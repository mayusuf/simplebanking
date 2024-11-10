package com.secured.banking.feature.user.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private Long id;

    private String username;
    private String firstName;
    private String lastName;

    private String email;
    private String mobile;
    private String occupation;
    private Boolean isLocked;
    private Boolean isTokenExpired;
    private String role;
    private String password;
}
