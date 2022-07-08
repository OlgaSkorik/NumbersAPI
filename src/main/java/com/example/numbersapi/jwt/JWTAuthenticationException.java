package com.example.numbersapi.jwt;


import org.springframework.security.core.AuthenticationException;

public class JWTAuthenticationException extends AuthenticationException {
    public JWTAuthenticationException(String message, Throwable throwable) {
        super(message, throwable);
    }
    public JWTAuthenticationException(String message) {
        super(message);
    }
}
