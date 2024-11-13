package com.example.BookStore.service.Impl;

import com.example.BookStore.dto.mapper.AccountMapper;
import com.example.BookStore.dto.request.CreateAccountRequest;
import com.example.BookStore.dto.request.UpdateAccountRequest;
import com.example.BookStore.dto.response.AccountDTO;
import com.example.BookStore.entity.base.Account;
import com.example.BookStore.exception.AppException;
import com.example.BookStore.exception.ErrorCode;
import com.example.BookStore.repo.AccountRepository;
import com.example.BookStore.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.sql.rowset.BaseRowSet;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public AccountDTO createAccount(CreateAccountRequest request){

        accountRepository.findByUsername(request.getUsername()).ifPresent(
                account -> { throw new AppException(ErrorCode.USER_EXISTED); }
        );

        Account account = Account.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .role(request.getRole())
                .build();
        accountRepository.save(account);

        return accountMapper.toAccountDTO(account);
    }
    @Override
    public List<AccountDTO> getAccounts(){
        return accountRepository.findAll()
                .stream()
                .map(accountMapper::toAccountDTO)
                .toList();
    }
    @Override
    public AccountDTO getAccountById(Long id){
        return accountRepository.findById(id)
                .map(accountMapper::toAccountDTO)
                .orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED)
        );
    }
    @Override
    public AccountDTO updateAccount(Long id, UpdateAccountRequest request){
        Account existAccount = accountRepository.findById(id).orElseThrow(
                        () -> new AppException(ErrorCode.USER_NOT_EXISTED));

        existAccount.setPassword(passwordEncoder.encode(request.getPassword()));

        accountRepository.save(existAccount);

        return accountMapper.toAccountDTO(existAccount);

    }
    @Override
    public void deleteAccount(Long id){
        accountRepository.deleteById(id);
    }
}

