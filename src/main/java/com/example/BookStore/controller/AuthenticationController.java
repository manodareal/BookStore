package com.example.BookStore.controller;

import com.example.BookStore.dto.request.AuthenticationRequest;
import com.example.BookStore.dto.request.IntrospectTokenResquest;
import com.example.BookStore.dto.response.ApiResponse;
import com.example.BookStore.dto.response.AuthenticationResponse;
import com.example.BookStore.dto.response.IntrospectTokenResponse;
import com.example.BookStore.service.AuthenticationService;
import lombok.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/login")
    public ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        var result = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/verify-token")
    public ApiResponse<IntrospectTokenResponse> introspectToken(@RequestBody IntrospectTokenResquest resquest){
        var result = authenticationService.introspect(resquest);
        return ApiResponse.<IntrospectTokenResponse>builder()
                .result(result)
                .build();
    }

}
