package com.example.BookStore.service;

import com.example.BookStore.config.jwt.JwtUtils;
import com.example.BookStore.dto.request.AuthenticationRequest;
import com.example.BookStore.dto.request.IntrospectTokenResquest;
import com.example.BookStore.dto.response.AuthenticationResponse;
import com.example.BookStore.dto.response.IntrospectTokenResponse;
import com.example.BookStore.entity.CustomUserDetails;
import com.example.BookStore.entity.User;
import com.example.BookStore.entity.base.Account;
import com.example.BookStore.exception.AppException;
import com.example.BookStore.exception.ErrorCode;
import com.example.BookStore.repo.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AccountRepository accountRepository;
    private final JwtUtils jwtUtils; // Đảm bảo JwtUtils được inject qua constructor
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    public IntrospectTokenResponse introspect(IntrospectTokenResquest resquest){
        var token = resquest.getToken();

        boolean isValid = jwtUtils.verifyToken(token);

        return IntrospectTokenResponse.builder()
                .valid(isValid)
                .build();

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        UserDetails userDetails = customUserDetailsService.loadUserByEmail(request.getEmail());

        if (userDetails == null) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        if (userDetails instanceof CustomUserDetails customUserDetails) {
            Account account = customUserDetails.getAccount();
            if (account instanceof User user) {
                if (!user.getEnabled()) {
                    throw new AppException(ErrorCode.ACCOUNT_NOT_ENABLED);
                }
            }
        }
        if (!passwordEncoder.matches(request.getPassword(), userDetails.getPassword())) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        var token = jwtUtils.generateToken(userDetails.getUsername());

        return AuthenticationResponse.builder()
                .token(token)
                .isAuthenticated(true)
                .build();
    }
}

