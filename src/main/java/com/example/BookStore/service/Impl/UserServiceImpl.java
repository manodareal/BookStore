package com.example.BookStore.service.Impl;

import com.example.BookStore.dto.request.RegistrationRequest;
import com.example.BookStore.entity.User;
import com.example.BookStore.entity.base.Account;
import com.example.BookStore.enumration.Role;
import com.example.BookStore.exception.AppException;
import com.example.BookStore.exception.ErrorCode;
import com.example.BookStore.regtoken.RegToken;
import com.example.BookStore.repo.AccountRepository;
import com.example.BookStore.repo.UserRepository;
import com.example.BookStore.service.RegTokenService;
import com.example.BookStore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.example.BookStore.enumration.Role.ROLE_USER;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final RegTokenService regTokenService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public String registerUser(RegistrationRequest request) {
        userRepository.findByEmail(request.getEmail()).ifPresent(
                account -> { throw new AppException(ErrorCode.USER_EXISTED); }
        );

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setRole(Role.ROLE_USER);

        user.setEnabled(false);
        userRepository.save(user);


        String token = UUID.randomUUID().toString();
        RegToken regToken = new RegToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(10),
                user
        );
        regTokenService.saveConfirmationToken(regToken);
        return token;
    }
    @Override
    public String resendConfirmationToken(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        if (user.getEnabled()) {
            throw new AppException(ErrorCode.ACCOUNT_ALREADY_ENABLED);
        }

        String newToken = UUID.randomUUID().toString();
        RegToken regToken = new RegToken(
                newToken,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(10),
                user
        );

        regTokenService.saveConfirmationToken(regToken);

        return newToken;
    }
    @Override
    public String confirmUserToken(String token) {
        RegToken regToken = regTokenService.getToken(token)
                .orElseThrow(() -> new AppException(ErrorCode.TOKEN_NOT_FOUND));

        if (regToken.getConfirmedAt() != null) {
            throw new IllegalStateException("Email already confirmed.");
        }

        LocalDateTime expiredAt = regToken.getExpiresAt();
        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Token expired.");
        }

        regTokenService.setConfirmedAt(token);
        enableUser(regToken.getUser().getEmail());
        return "Email Confirmed";
    }

    @Override
    public void enableUser(String email) {
        userRepository.enableUser(email);
    }

}
