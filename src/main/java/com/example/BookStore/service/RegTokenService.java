package com.example.BookStore.service;

import com.example.BookStore.regtoken.RegToken;

import java.util.Optional;

public interface RegTokenService {
    void saveConfirmationToken(RegToken regToken);

    Optional<RegToken> getToken(String regToken);

    int setConfirmedAt(String token);

}
