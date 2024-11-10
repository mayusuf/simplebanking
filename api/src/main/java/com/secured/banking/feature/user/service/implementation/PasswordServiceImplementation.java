package com.secured.banking.feature.user.service.implementation;

import com.secured.banking.feature.user.dao.Password;
import com.secured.banking.feature.user.dao.User;
import com.secured.banking.feature.user.repository.PasswordRepository;
import com.secured.banking.feature.user.service.PasswordService;
import com.secured.banking.security.service.PasswordEncoderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordServiceImplementation implements PasswordService {

    private final PasswordRepository passwordRepository;

    @Value("${application.user.default.raw.password}")
    private String defaultPassword;

    public Password setDefaultPassword(User user) {
        return save(user, defaultPassword);
    }

    public Password save(User user, String rawPassword) {
        Password password = Password.builder()
                .hashed(new PasswordEncoderService(PasswordEncoderService.EncodeAlgorithm.sha512).encode(rawPassword))
                .user(user)
                .build();

        return passwordRepository.save(password);
    }

}
