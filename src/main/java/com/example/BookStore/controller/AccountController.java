package com.example.BookStore.controller;

import com.example.BookStore.dto.request.CreateAccountRequest;
import com.example.BookStore.dto.request.UpdateAccountRequest;
import com.example.BookStore.dto.response.AccountDTO;
import com.example.BookStore.dto.response.ApiResponse;
import com.example.BookStore.entity.Account;
import com.example.BookStore.repo.AccountRepository;
import com.example.BookStore.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAccounts(){

        List<AccountDTO> accounts = accountService.getAccounts();
        return ResponseEntity.ok(ApiResponse.builder()
                .code(HttpStatus.OK.value())
                .message("Created user successfully")
                .result(accounts)
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getAccount(@PathVariable Long id){

        AccountDTO existedAccount = accountService.getAccountById(id);

        return ResponseEntity.ok(ApiResponse.builder()
                        .code(HttpStatus.OK.value())
                        .message("Get user information successfully")
                        .result(existedAccount)
                .build());
    }
    @PostMapping("/create")

    public ResponseEntity<ApiResponse> createAccount(@RequestBody @Valid CreateAccountRequest request){
        AccountDTO newAccount = accountService.createAccount(request);
        ApiResponse response = ApiResponse.<AccountDTO>builder()
                .code(HttpStatus.CREATED.value())
                .message("Created user successfully")
                .result(newAccount)
                .build();
//        return ResponseEntity.ok(ApiResponse.builder()
//                        .code(HttpStatus.CREATED.value())
//                        .message("Created user successfully")
//                        .result(newAccount)
//                .build());
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }
    @PutMapping("/{id}/update")
    public ResponseEntity<ApiResponse> updateAccount(@PathVariable Long id, @RequestBody @Valid UpdateAccountRequest request){
        AccountDTO account = accountService.updateAccount(id, request);
        ApiResponse response = ApiResponse.<AccountDTO>builder()
                .code(HttpStatus.OK.value())
                .message("Updated user successfully")
                .result(account)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public void deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
    }
}
