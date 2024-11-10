package com.secured.banking.feature.user.service;

import com.secured.banking.feature.user.dao.User;
import com.secured.banking.feature.user.payload.UserRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    User register(UserRequest newUser);

    Optional<User> findByUsername(String username);
}
