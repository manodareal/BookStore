package com.example.BookStore.controller;

import com.example.BookStore.dto.request.AuthenticationRequest;
import com.example.BookStore.dto.response.ApiResponse;
import com.example.BookStore.dto.response.AuthenticationResponse;
import com.example.BookStore.service.AuthenticationService;
import lombok.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/login")
    public ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest){
        var result = authenticationService.authenticate(authenticationRequest);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

}
