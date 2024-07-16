package com.example.finx.user.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    private String generateToken(String id) {
        return Jwts.builder()
                .setSubject(id)
                .signWith(SignatureAlgorithm.HS256, "1234")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + (999999999 * 1000000000)))
                .compact();
    }

    public TokenResponse generateTokens(Long userId) {
        String access = generateToken(userId.toString());

        return TokenResponse.builder()
                .accessToken(access)
                .build();
    }
}
