package com.example.BookStore.dto.request;

import com.example.BookStore.enumration.Role;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistrationRequest {
    @Size(min = 3, message = "USERNAME_INVALID")
    private String username;

    @Size(min = 8, message = "PASSWORD_INVALID")
    @NotEmpty(message = "Please provide a password")
    private String password;

    @Pattern(regexp = "[a-zA-Z][a-zA-Z ]+")
    @NotEmpty(message = "Please provide your first name")
    private String firstName;
    @Pattern(regexp = "[a-zA-Z][a-zA-Z ]+")
    @NotEmpty(message = "Please provide your last name")
    private String lastName;
    @Email
    private String email;
    private LocalDate dateOfBirth;
}
