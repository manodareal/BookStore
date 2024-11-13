package com.example.BookStore.service.Impl;

import com.example.BookStore.regtoken.RegToken;
import com.example.BookStore.repo.RegTokenRepository;
import com.example.BookStore.service.RegTokenService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegTokenServiceImpl implements RegTokenService {
    private final RegTokenRepository regTokenRepository;

    @Override
    public void saveConfirmationToken(RegToken regToken) {
        regTokenRepository.save(regToken);
    }

    @Override
    public Optional<RegToken> getToken(String token) {
        return regTokenRepository.findByToken(token);
    }

    @Override
    public int setConfirmedAt(String token) {
        return regTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }
}
