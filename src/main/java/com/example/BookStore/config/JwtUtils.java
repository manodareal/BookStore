package com.example.BookStore.config;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtUtils {

    private final byte[] secretKey;
    private final long expirationTimeMs = 1000 * 60 * 60; // 1 giờ

    public JwtUtils() {
        this.secretKey = "this_is_a_book_online_website_secretkey".getBytes();  // Chuyển đổi khóa bí mật thành byte[]
    }

    public String generateToken(String username) {
        // 1. Tạo JWT Header
        JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.HS256)
                .type(JOSEObjectType.JWT) // Thêm kiểu token (JWT)
                .build();

        // 2. Tạo JWT Claims
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .issuer("BookStore")
                .issueTime(new Date())
                .expirationTime(new Date(new Date().getTime() + expirationTimeMs))
                .claim("customClaim", "custom")
                .build();

        // 3. Tạo SignedJWT
        SignedJWT signedJWT = new SignedJWT(header, jwtClaimsSet);

        // 4. Ký JWT bằng secret key
        try {
            signedJWT.sign(new MACSigner(secretKey));
        } catch (JOSEException e) {
            log.error("Cannot generate tokem", e);
            throw new RuntimeException("Error signing the JWT token: " + e.getMessage(), e);
        }

        // 5. Trả về token dạng chuỗi
        return signedJWT.serialize();
    }
}
