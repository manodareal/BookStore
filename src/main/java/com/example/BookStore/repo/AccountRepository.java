package com.example.BookStore.repo;

import com.example.BookStore.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsByUsername(String username);
    Optional<Account> findByUsername(String username);
}
