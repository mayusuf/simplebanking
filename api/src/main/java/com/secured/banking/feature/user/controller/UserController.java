package com.secured.banking.feature.user.controller;

import com.secured.banking.exception.CommonException;
import com.secured.banking.feature.user.dao.User;
import com.secured.banking.feature.user.payload.LoginRequest;
import com.secured.banking.feature.user.payload.TokenResponse;
import com.secured.banking.feature.user.payload.UserRequest;
import com.secured.banking.feature.user.service.UserService;
import com.secured.banking.security.handler.LoginAttemptHandler;
import com.secured.banking.security.service.TokenManagerService;
import com.secured.banking.utility.message.ErrorMessage;
import com.secured.banking.utility.route.RouteInformation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(RouteInformation.API_ROUTE_V1)
public class UserController {

    private final UserService userService;

    private final TokenManagerService tokenManager;
    private final LoginAttemptHandler loginHandler;
    private final AuthenticationManager authenticationManager;

    @PostMapping(RouteInformation.SIGN_IN_ROUTE)
    public ResponseEntity<?> getToken(@RequestBody @Valid LoginRequest request, HttpServletRequest servletRequest) {

        if (loginHandler.isBlocked(servletRequest)) {
            throw new CommonException(ErrorMessage.USER_IP_BLOCK);
        } else {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            User user = (User) authentication.getPrincipal();
            String token = tokenManager.generateToken(user.getUsername());

            loginHandler.loginSucceeded(servletRequest);
            return ResponseEntity.ok(new TokenResponse(token, request.getUsername()));
        }
    }

    @PostMapping(RouteInformation.REGISTRATION_ROUTE)
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserRequest request, HttpServletRequest servletRequest) {
        User user = userService.register(request);

        // TODO :: Cannot send user as there is relationship need separate DTO
        return ResponseEntity.ok(user.getUsername());
    }
}
