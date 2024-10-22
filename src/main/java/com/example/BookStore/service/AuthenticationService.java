package com.example.BookStore.service;

import com.example.BookStore.config.JwtUtils;
import com.example.BookStore.dto.request.AuthenticationRequest;
import com.example.BookStore.dto.response.AuthenticationResponse;
import com.example.BookStore.exception.AppException;
import com.example.BookStore.exception.ErrorCode;
import com.example.BookStore.repo.AccountRepository;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AccountRepository accountRepository;
    private final JwtUtils jwtUtils; // Đảm bảo JwtUtils được inject qua constructor

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var account = accountRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(request.getPassword(), account.getPassword());

        if (!authenticated) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        var token = jwtUtils.generateToken(request.getUsername());

        return AuthenticationResponse.builder()
                .token(token)
                .isAuthenticated(true)
                .build();
    }
}

