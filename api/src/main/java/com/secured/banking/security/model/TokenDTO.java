package com.secured.banking.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenDTO {
    private String subject;
    private String message;
    private Boolean status;

    public TokenDTO() {
        this.status = false;
    }
}
