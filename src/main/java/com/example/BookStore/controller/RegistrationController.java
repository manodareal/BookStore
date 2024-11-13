package com.example.BookStore.controller;

import com.example.BookStore.dto.request.RegistrationRequest;
import com.example.BookStore.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<String> register(@RequestBody @Valid RegistrationRequest request) {
        return ResponseEntity.ok(userService.registerUser(request));
    }
    @GetMapping("/resend-token")
    public ResponseEntity<String> resendToken(@RequestParam("email") String email) {
        return ResponseEntity.ok(userService.resendConfirmationToken(email));
    }
    @GetMapping("/confirm")
    public ResponseEntity<String> confirmToken(@RequestParam("token") String token) {
        return ResponseEntity.ok(userService.confirmUserToken(token));
    }
}
