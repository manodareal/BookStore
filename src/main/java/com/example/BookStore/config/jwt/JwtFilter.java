package com.example.BookStore.config.jwt;

import com.example.BookStore.entity.base.Account;
import com.example.BookStore.exception.AppException;
import com.example.BookStore.exception.ErrorCode;
import com.example.BookStore.repo.AccountRepository;
import com.example.BookStore.service.CustomUserDetailsService;
import com.nimbusds.jwt.SignedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.text.ParseException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {
    private final CustomUserDetailsService customUserDetailsService;

    private final JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        final String tokenHeader = request.getHeader("Authorization");
        final String token;
        final String email;

        if (tokenHeader == null || !tokenHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        token = tokenHeader.substring(7);

        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            email = signedJWT.getJWTClaimsSet().getSubject(); // Lấy email từ subject của token

            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                if (jwtUtils.verifyToken(token)) {
                    UserDetails userDetails = customUserDetailsService.loadUserByEmail(email); // Sử dụng email
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }

        } catch (ParseException e) {
            log.error("Token parsing failed", e);
        }

        filterChain.doFilter(request, response);
    }
}
