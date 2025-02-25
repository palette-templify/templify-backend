package com.ktds.templify.auth.service;

import com.ktds.templify.auth.dto.TokenResponse;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenProvider {
    
    private final String secret;
    private final long expiration;
    
    public TokenResponse createToken(Authentication authentication) {
        // JWT 토큰 생성 로직
        String accessToken = Jwts.builder()
            .setSubject(authentication.getName())
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact();
            
        String refreshToken = createRefreshToken(authentication.getName());
        
        return new TokenResponse(accessToken, refreshToken);
    }
    
    private String createRefreshToken(String userId) {
        // Refresh 토큰 생성 로직
        return Jwts.builder()
            .setSubject(userId)
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact();
    }
}
