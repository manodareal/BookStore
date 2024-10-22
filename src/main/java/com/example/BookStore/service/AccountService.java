package com.example.BookStore.service;

import com.example.BookStore.dto.request.CreateAccountRequest;
import com.example.BookStore.dto.request.UpdateAccountRequest;
import com.example.BookStore.entity.Account;
import com.example.BookStore.exception.AppException;
import com.example.BookStore.exception.ErrorCode;
import com.example.BookStore.repo.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;


    public Account createAccount(CreateAccountRequest request){

        if(accountRepository.existsByUsername(request.getUsername())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        Account account = Account.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .dateOfBirth(request.getDateOfBirth())
                .build();

        return accountRepository.save(account);
    }

    public List<Account> getAccounts(){
        return accountRepository.findAll();
    }

    public Account getAccountById(Long id){
        return accountRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.USER_EXISTED)
        );
    }

    public Account updateAccount(Long id, UpdateAccountRequest request){
        Account account = getAccountById(id);

        account.setPassword(request.getPassword());
        account.setFirstName(request.getFirstName());
        account.setLastName(request.getLastName());
        account.setDateOfBirth(request.getDateOfBirth());

        return accountRepository.save(account);

    }

    public void deleteAccount(Long id){
        accountRepository.deleteById(id);
    }
}

