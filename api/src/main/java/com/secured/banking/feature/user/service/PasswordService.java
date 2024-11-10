package com.secured.banking.feature.user.service;

import com.secured.banking.feature.user.dao.Password;
import com.secured.banking.feature.user.dao.User;

public interface PasswordService {
    Password setDefaultPassword(User user);
}
