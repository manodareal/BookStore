package com.example.BookStore.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationRequest {
    @Email
    private String email;

    @Size(min = 8, message = "PASSWORD_INVALID")
    @NotEmpty(message = "Please provide a password")
    private String password;

}
