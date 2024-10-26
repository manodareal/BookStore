package com.example.BookStore.service;

import com.example.BookStore.dto.mapper.AccountMapper;
import com.example.BookStore.dto.request.CreateAccountRequest;
import com.example.BookStore.dto.request.UpdateAccountRequest;
import com.example.BookStore.dto.response.AccountDTO;
import com.example.BookStore.entity.Account;
import com.example.BookStore.exception.AppException;
import com.example.BookStore.exception.ErrorCode;
import com.example.BookStore.repo.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountDTO createAccount(CreateAccountRequest request){

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
        accountRepository.save(account);

        return accountMapper.toAccountDTO(account);
    }

    public List<AccountDTO> getAccounts(){
        return accountRepository.findAll()
                .stream()
                .map(accountMapper::toAccountDTO)
                .toList();
    }

    public AccountDTO getAccountById(Long id){
        return accountRepository.findById(id)
                .map(accountMapper::toAccountDTO)
                .orElseThrow(
                () -> new AppException(ErrorCode.USER_EXISTED)
        );
    }

    public AccountDTO updateAccount(Long id, UpdateAccountRequest request){
        Account existAccount = accountRepository.findById(id).orElseThrow(
                        () -> new AppException(ErrorCode.USER_EXISTED));

        existAccount.setPassword(request.getPassword());
        existAccount.setFirstName(request.getFirstName());
        existAccount.setLastName(request.getLastName());
        existAccount.setDateOfBirth(request.getDateOfBirth());

        accountRepository.save(existAccount);

        return accountMapper.toAccountDTO(existAccount);

    }

    public void deleteAccount(Long id){
        accountRepository.deleteById(id);
    }
}

