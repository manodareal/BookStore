package com.example.BookStore.dto.mapper;

import com.example.BookStore.dto.response.AccountDTO;
import com.example.BookStore.entity.base.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public AccountDTO toAccountDTO(Account account){
        return new AccountDTO(
                account.getId(),
                account.getUsername(),
                account.getFirstName(),
                account.getEmail(),
                account.getLastName(),
                account.getDateOfBirth()
        );
    }
}
