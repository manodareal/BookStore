package com.example.BookStore.repo;

import com.example.BookStore.regtoken.RegToken;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface RegTokenRepository extends JpaRepository<RegToken, Long> {
    Optional<RegToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query("UPDATE RegToken c " +
            "SET c.confirmedAt = ?2 " +
            "WHERE c.token = ?1")
    int updateConfirmedAt(String token,
                          LocalDateTime confirmedAt);
}
