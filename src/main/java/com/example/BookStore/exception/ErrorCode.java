package com.example.BookStore.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(999, "Uncategorized Error"),
    USER_EXISTED(1001, "user existed"),
    USER_NOT_EXISTED(1002, "User not existed"),
    USERNAME_INVALID(1003, "Username must be at least 3 characters"),
    PASSWORD_INVALID(1004, "Password must be at least 8 characters"),
    UNAUTHENTICATED(1005, "Unauthenticated")

    ;
    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
