package com.secured.banking.exception;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.sql.SQLException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException exception) {
        log.error(exception.toString());
        return new ResponseEntity<>("ACCESS DENIED", HttpStatus.FORBIDDEN);
    }

    @ResponseBody
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<?> handleSQLException(SQLException exception) {
        log.error(exception.toString());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public ResponseEntity<?> handleInternalServerException(HttpServerErrorException.InternalServerError exception) {
        log.error(exception.toString());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception exception) {
        log.error(exception.toString());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @ExceptionHandler(CommonException.class)
    public ResponseEntity<?> handleCommonException(CommonException exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> handleAuthenticationException(ResponseStatusException exception, HttpServletResponse response) {
        log.error("status ::{} -> reason :: {}", response.getStatus(), exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatusCode.valueOf(response.getStatus()));
    }
}
