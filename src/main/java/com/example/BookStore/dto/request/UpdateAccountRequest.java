package com.example.BookStore.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateAccountRequest {
    @Size(min = 8, message = "PASSWORD_INVALID")
    @NotEmpty(message = "Please provide a password")
    private String password;

    @Pattern(regexp = "[a-zA-Z][a-zA-Z ]+")
    @NotEmpty(message = "Please provide your first name")
    private String firstName;
    @Pattern(regexp = "[a-zA-Z][a-zA-Z ]+")
    @NotEmpty(message = "Please provide your last name")
    private String lastName;

    private LocalDate dateOfBirth;
}
