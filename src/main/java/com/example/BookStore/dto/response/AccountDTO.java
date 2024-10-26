package com.example.BookStore.dto.response;

import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

    private String username;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

}
