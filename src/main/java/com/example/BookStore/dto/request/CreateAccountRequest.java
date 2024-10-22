package com.example.BookStore.dto.request;

        import com.example.BookStore.exception.ErrorCode;
        import jakarta.validation.constraints.Size;
        import lombok.*;

        import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateAccountRequest {
    @Size(min = 3, message = "USERNAME_INVALID")
    private String username;

    @Size(min = 8, message = "PASSWORD_INVALID")
    private String password;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

}
