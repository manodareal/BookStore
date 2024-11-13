package com.example.BookStore.service;

import com.example.BookStore.dto.request.CreateAccountRequest;
import com.example.BookStore.dto.request.RegistrationRequest;
import com.example.BookStore.dto.request.UpdateAccountRequest;
import com.example.BookStore.dto.response.AccountDTO;

import java.util.List;

public interface UserService {
    String registerUser(RegistrationRequest request);
    String confirmUserToken(String token);
    String resendConfirmationToken(String email);
    void enableUser(String email);


}
