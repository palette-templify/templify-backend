package com.ktds.templify.auth.service;

import com.ktds.templify.auth.dto.LoginRequest;
import com.ktds.templify.auth.dto.TokenResponse;
import com.ktds.templify.auth.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final AuthRepository authRepository;
    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    
    @Transactional
    public TokenResponse login(LoginRequest request) {
        var authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        
        return tokenProvider.createToken(authentication);
    }
}
