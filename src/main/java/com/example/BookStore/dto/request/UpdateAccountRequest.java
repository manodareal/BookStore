package com.example.BookStore.dto.request;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateAccountRequest {
    private String password;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;
}
