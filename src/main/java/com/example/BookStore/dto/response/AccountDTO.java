package com.example.BookStore.dto.response;

import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

    private Long id;
    private String username;

    private String firstName;
    private String email;

    private String lastName;

    private LocalDate dateOfBirth;

}
