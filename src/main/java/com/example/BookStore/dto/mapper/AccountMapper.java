package com.example.BookStore.dto.mapper;

import com.example.BookStore.dto.response.AccountDTO;
import com.example.BookStore.entity.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public AccountDTO toAccountDTO(Account account){
        return new AccountDTO(
                account.getUsername(),
                account.getFirstName(),
                account.getLastName(),
                account.getDateOfBirth()
        );
    }
}
