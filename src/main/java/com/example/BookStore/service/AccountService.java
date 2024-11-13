package com.example.BookStore.service;

import com.example.BookStore.dto.request.CreateAccountRequest;
import com.example.BookStore.dto.request.UpdateAccountRequest;
import com.example.BookStore.dto.response.AccountDTO;

import java.util.List;

public interface AccountService {

    AccountDTO createAccount(CreateAccountRequest request);
    List<AccountDTO> getAccounts();
    AccountDTO getAccountById(Long id);
    AccountDTO updateAccount(Long id, UpdateAccountRequest request);
    void deleteAccount(Long id);

}
