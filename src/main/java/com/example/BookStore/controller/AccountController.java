package com.example.BookStore.controller;

import com.example.BookStore.dto.request.CreateAccountRequest;
import com.example.BookStore.dto.request.UpdateAccountRequest;
import com.example.BookStore.dto.response.ApiResponse;
import com.example.BookStore.entity.Account;
import com.example.BookStore.repo.AccountRepository;
import com.example.BookStore.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/create")
    public ApiResponse<Account> createAccount(@RequestBody @Valid CreateAccountRequest request){
        ApiResponse<Account> apiResponse = new ApiResponse<>();

        apiResponse.setResult(accountService.createAccount(request));

        return apiResponse;
    }

    @GetMapping("/")
    public List<Account> getAccounts(){

        return accountService.getAccounts();
    }

    @GetMapping("/{id}")
    public ApiResponse<Account> getAccount(@PathVariable Long id){
        ApiResponse<Account> apiResponse = new ApiResponse<>();

        apiResponse.setResult(accountService.getAccountById(id));

        return apiResponse;
    }

    @PutMapping("/{id}/update")
    public ApiResponse<Account> updateAccount(@PathVariable Long id, @RequestBody UpdateAccountRequest request){
        ApiResponse<Account> apiResponse = new ApiResponse<>();

        apiResponse.setResult(accountService.updateAccount(id, request));

        return apiResponse;
    }

    @DeleteMapping("/{id}/delete")
    public void deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
    }
}
