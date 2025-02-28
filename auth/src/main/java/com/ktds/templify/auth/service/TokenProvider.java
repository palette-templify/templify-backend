package com.ktds.templify.auth.service;

import com.ktds.templify.auth.dto.TokenResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenProvider {
    
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    public TokenResponse createToken(Authentication authentication) {
        Date now = new Date();
        Date accessExpiryDate = new Date(now.getTime() + expiration);

        long userId = Long.parseLong(authentication.getName());

        // JWT 토큰 생성 로직
        String accessToken = Jwts.builder()
            .setSubject("accessToken")
            .claim("userId", userId)
            .setIssuedAt(now)
            .setExpiration(accessExpiryDate)
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact();
            
        String refreshToken = createRefreshToken(userId);
        
        return new TokenResponse(accessToken, refreshToken);
    }
    
    private String createRefreshToken(Long userId) {
        Date now = new Date();
        Date refreshExpiryDate = new Date(now.getTime() + (expiration * 7));

        // Refresh 토큰 생성 로직
        return Jwts.builder()
            .setSubject("refreshToken")
            .claim("userId", userId)
            .setIssuedAt(now)
            .setExpiration(refreshExpiryDate)
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact();
    }
}
