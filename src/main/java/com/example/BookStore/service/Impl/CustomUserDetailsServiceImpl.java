package com.example.BookStore.service.Impl;

import com.example.BookStore.entity.CustomUserDetails;
import com.example.BookStore.entity.User;
import com.example.BookStore.entity.base.Account;
import com.example.BookStore.repo.AccountRepository;
import com.example.BookStore.repo.UserRepository;
import com.example.BookStore.service.CustomUserDetailsService;
import com.example.BookStore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new CustomUserDetails(account);  // Trả về CustomUserDetails chứa Account
    }
    @Override
    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return new CustomUserDetails(user);  // Return CustomUserDetails containing User
    }


}
