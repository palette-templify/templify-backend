package com.ktds.templify.auth.service;

import com.ktds.templify.auth.dto.TokenResponse;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

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

        // JWT 토큰 생성 로직
        String accessToken = Jwts.builder()
            .setSubject(authentication.getName())
            .setIssuedAt(now)
            .setExpiration(accessExpiryDate)
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact();
            
        String refreshToken = createRefreshToken(authentication.getName());
        
        return new TokenResponse(accessToken, refreshToken);
    }
    
    private String createRefreshToken(String userId) {
        Date now = new Date();
        Date refreshExpiryDate = new Date(now.getTime() + (expiration * 7));

        // Refresh 토큰 생성 로직
        return Jwts.builder()
            .setSubject(userId)
            .setIssuedAt(now)
            .setExpiration(refreshExpiryDate)
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact();
    }
}
