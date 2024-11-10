package com.secured.banking.feature.user.service.implementation;

import com.secured.banking.exception.CommonException;
import com.secured.banking.feature.user.dao.Password;
import com.secured.banking.feature.user.dao.User;
import com.secured.banking.feature.user.payload.UserRequest;
import com.secured.banking.feature.user.repository.UserRepository;
import com.secured.banking.feature.user.service.PasswordService;
import com.secured.banking.feature.user.service.RoleService;
import com.secured.banking.feature.user.service.UserService;
import com.secured.banking.utility.message.ErrorMessage;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;

    private final PasswordService passwordService;
    private final RoleService roleService;

    @Transactional
    public User register(UserRequest newUser) {

        Optional<User> optionalUser = findByUsername(newUser.getUsername());

        if (optionalUser.isPresent())
            throw new CommonException(ErrorMessage.USERNAME_EXIST);

        User user = User.builder()
                .firstName(newUser.getFirstName())
                .lastName(newUser.getLastName())
                .username(newUser.getUsername())
                .email(newUser.getEmail())
                .mobile(newUser.getMobile())
                .occupation(newUser.getOccupation())
                .isLocked(false)
                .isTokenExpired(false)
                .role(roleService.setDefaultRole())
                .build();

        user = userRepository.save(user);
        Password password = passwordService.setDefaultPassword(user);
        user.setPassword(List.of(password));

        return user;
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

}
