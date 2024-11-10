package com.secured.banking.security;

import com.secured.banking.feature.user.service.UserService;
import com.secured.banking.security.filter.AuthenticationTokenFilter;
import com.secured.banking.security.handler.ApplicationAccessDeniedHandler;
import com.secured.banking.security.handler.AuthenticationEntryPointHandler;
import com.secured.banking.security.handler.LoginAttemptHandler;
import com.secured.banking.security.service.PasswordEncoderService;
import com.secured.banking.security.service.TokenManagerService;
import com.secured.banking.utility.constant.CorsVariable;
import com.secured.banking.utility.constant.ServletVariable;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final UserService userService;
    private final TokenManagerService tokenManager;
    private final LoginAttemptHandler loginAttemptHandler;

    private static CorsConfiguration getCorsConfiguration() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(CorsVariable.ALLOW_CREDENTIAL);
        corsConfiguration.setAllowedHeaders(Arrays.asList(CorsVariable.ALLOWED_HEADERS));
        corsConfiguration.setAllowedMethods(Arrays.asList(CorsVariable.ALLOWED_METHODS));
        corsConfiguration.setExposedHeaders(Collections.singletonList(CorsVariable.EXPOSED_HEADER));
        corsConfiguration.setAllowedOriginPatterns(Collections.singletonList(CorsVariable.ALLOWED_ORIGIN_PATTERN));
        return corsConfiguration;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoderService();
    }

    @Bean
    public ApplicationAccessDeniedHandler accessDeniedHandler() {
        return new ApplicationAccessDeniedHandler();
    }

    @Bean
    public AuthenticationEntryPointHandler authenticationEntryPoint() {
        return new AuthenticationEntryPointHandler(loginAttemptHandler);
    }

    @Bean
    public AuthenticationTokenFilter authenticationTokenFilter() {
        return new AuthenticationTokenFilter(userService, tokenManager);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable);
        http.formLogin(AbstractHttpConfigurer::disable);
        http.httpBasic(AbstractHttpConfigurer::disable);

        http.cors(cors -> cors.configurationSource(request -> getCorsConfiguration()));

        http.exceptionHandling(exceptionHandling -> exceptionHandling.accessDeniedHandler(accessDeniedHandler()));
        http.exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(authenticationEntryPoint()));

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        http.authorizeHttpRequests(request -> request
                .requestMatchers(ServletVariable.NEED_AUTHORIZATION_URLS).authenticated()
                .requestMatchers(ServletVariable.OPEN_URLS).permitAll()
                .anyRequest().permitAll());

        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder());

        http.authenticationManager(authenticationManagerBuilder.build());

        return http.build();
    }
}
