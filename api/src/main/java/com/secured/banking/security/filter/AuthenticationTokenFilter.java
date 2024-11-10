package com.secured.banking.security.filter;


import com.secured.banking.exception.CommonException;
import com.secured.banking.feature.user.service.UserService;
import com.secured.banking.security.model.TokenDTO;
import com.secured.banking.security.service.TokenManagerService;
import com.secured.banking.utility.constant.JwtVariable;
import com.secured.banking.utility.constant.ServletVariable;
import com.secured.banking.utility.message.ErrorMessage;
import com.secured.banking.utility.message.LogPurpose;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

public class AuthenticationTokenFilter extends AuthenticationFilter {
    private final UserService userService;
    private final TokenManagerService tokenManager;

    @Value("${application.date.formatter}")
    private String dateTimeFormatter;

    public AuthenticationTokenFilter(UserService userService, TokenManagerService tokenManager) {
        super(
                new ProviderManager(Collections.singletonList(new DaoAuthenticationProvider())),
                new BasicAuthenticationConverter()
        );
        this.userService = userService;
        this.tokenManager = tokenManager;
    }

    private Boolean checkOpenURL(String uri) {
        boolean check = Boolean.FALSE;
        for (String url : ServletVariable.TOKEN_FILTER_OPEN_URLS) {
            check = uri.contains(url);
            if (check)
                break;
        }
        return check;
    }

    private Boolean matchOpenURL(String uri) {
        boolean check = Boolean.FALSE;
        for (String url : ServletVariable.TOKEN_FILTER_MATCH_OPEN_URLS) {
            check = uri.matches(url);
            if (check)
                break;
        }
        return check;
    }

    private String getToken(HttpServletRequest httpServletRequest) {
        String requestHeader = httpServletRequest.getHeader(JwtVariable.SERVLET_REQUEST_HEADER);

        if (StringUtils.hasText(requestHeader) && requestHeader.startsWith(JwtVariable.TOKEN_PREFIX)) {
            return requestHeader.substring(JwtVariable.TOKEN_SUBSTRING_INDEX);
        }

        throw new CommonException(ErrorMessage.TOKEN_NOT_FOUND);
    }

    private void setErrorResponse(String HOST_IP, String URI, String message, Integer statusCode, HttpServletResponse servletResponse) throws IOException {
        logger.error(ErrorMessage.INTERNAL_FILTER_FAILED + URI + LogPurpose.FROM_IP + HOST_IP + " :: exception message :: " + message);

        ZonedDateTime now = LocalDateTime.now().atZone(ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateTimeFormatter);
        String formattedDateTime = formatter.format(now);

        servletResponse.setStatus(statusCode);
        servletResponse.setContentType("application/json");

        String errorMessage = "{" +
                "\"timestamp\": \"" + formattedDateTime + "\"," +
                "\"status\": \"" + HttpStatus.FORBIDDEN.value() + "\"," +
                "\"error\": \"" + message + "\"," +
                "\"path\": \"" + URI + "\"" +
                "}";

        servletResponse.getWriter().write(errorMessage);
        servletResponse.getWriter().flush();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest servletRequest,
                                    HttpServletResponse servletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        String URI = servletRequest.getRequestURI();
        String HOST_IP = servletRequest.getRemoteAddr();
        try {
            if (checkOpenURL(URI) || matchOpenURL(URI)) {
                logger.info(LogPurpose.IGNORE_OPEN_URL + URI + LogPurpose.FROM_IP + HOST_IP);
            } else {
                logger.info(LogPurpose.VALIDATE_URL + URI + LogPurpose.FROM_IP + HOST_IP);
                String token = getToken(servletRequest);
                TokenDTO validatedToken = tokenManager.validateToken(token);

                if (validatedToken.getStatus()) {
                    String username = validatedToken.getSubject();
                    UserDetails userDetails = userService.loadUserByUsername(username);

                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(servletRequest));
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                } else {
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN, validatedToken.getMessage());
                }
            }
        } catch (ResponseStatusException exception) {
            setErrorResponse(HOST_IP, URI, exception.getReason(), exception.getStatusCode().value(), servletResponse);
        } catch (Exception exception) {
            setErrorResponse(HOST_IP, URI, exception.getMessage(), HttpServletResponse.SC_INTERNAL_SERVER_ERROR, servletResponse);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
