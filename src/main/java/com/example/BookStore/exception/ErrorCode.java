package com.example.BookStore.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "Uncategorized Error"),
    USER_EXISTED(HttpStatus.CONFLICT, "User existed"),
    USER_NOT_EXISTED(HttpStatus.NOT_FOUND, "User not existed"),
    USERNAME_INVALID(HttpStatus.BAD_REQUEST, "Username must be at least 3 characters"),
    PASSWORD_INVALID(HttpStatus.BAD_REQUEST, "Password must be at least 8 characters"),
    UNAUTHENTICATED(HttpStatus.UNAUTHORIZED, "Unauthenticated"),
    TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "Token not found" ),
    ACCOUNT_NOT_ENABLED(HttpStatus.CONFLICT, "Account not enabled" ),
    ACCOUNT_ALREADY_ENABLED(HttpStatus.BAD_REQUEST,"Account already enabled" );

    private final HttpStatus httpStatus;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public int getCode() {
        return httpStatus.value();
    }

    public String getReasonPhrase() {
        return httpStatus.getReasonPhrase();
    }
}
