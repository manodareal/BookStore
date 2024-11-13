package com.example.BookStore.entity;

import com.example.BookStore.entity.base.Account;
import com.example.BookStore.enumration.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "app_user")
public class User extends Account implements UserDetails {
    private Boolean enabled;

    public User(String username, String password, String email, String firstName, String lastName, LocalDate dateOfBirth, Role role) {
        super(username, password, email, firstName, lastName, dateOfBirth, role);
        this.enabled = false;
    }
}
