package com.example.BookStore.controller;

import com.example.BookStore.dto.request.AuthenticationRequest;
import com.example.BookStore.dto.request.IntrospectTokenResquest;
import com.example.BookStore.dto.response.ApiResponse;
import com.example.BookStore.dto.response.AuthenticationResponse;
import com.example.BookStore.dto.response.IntrospectTokenResponse;
import com.example.BookStore.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/login")
    public ApiResponse<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest request){
        var result = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Login successful")
                .result(result)
                .build();
    }
//    public ResponseEntity<Object> authenticate(@RequestBody AuthenticationRequest request) {
//        AuthenticationResponse result = authenticationService.authenticate(request);
//        return ApiResponse.builder()
//                .message("Login success")
//                .code(HttpStatus.ACCEPTED)
//                .result(result)
//                .build();
//    }
    @PostMapping("/verify-token")
    public ApiResponse<IntrospectTokenResponse> introspectToken(@RequestBody IntrospectTokenResquest resquest){
        var result = authenticationService.introspect(resquest);
        return ApiResponse.<IntrospectTokenResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Login successful")
                .result(result)
                .build();
    }
//    public ResponseEntity<Object> introspectToken(@RequestBody IntrospectTokenResquest request) {
//        IntrospectTokenResponse result = authenticationService.introspect(request);
//        return ApiResponse.generateResponse("Token verification successful", HttpStatus.OK, result);
//    }

}
