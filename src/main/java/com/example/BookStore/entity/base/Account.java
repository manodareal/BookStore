package com.example.BookStore.entity.base;

import com.example.BookStore.enumration.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Inheritance(strategy = InheritanceType.JOINED)

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq")
    @SequenceGenerator(name = "account_seq", sequenceName = "account_sequence", allocationSize = 1)
    private Long id;
    @Column
    private String username;
    @Column
    private String password;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private LocalDate dateOfBirth;
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    public Account(String username, String password, String email, String firstName, String lastName, LocalDate dateOfBirth) {
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    public Account(String username, String password, String email, String firstName, String lastName, LocalDate dateOfBirth, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUsername(){
        return email;
    }


}
