package com.example.usermanagement.service;

import com.example.usermanagement.entity.User;
import com.example.usermanagement.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    
    public AuthResponse login(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(email, password)
        );
        
        String token = jwtTokenProvider.generateToken(authentication);
        User user = userService.getUserByEmail(email);
        
        return new AuthResponse(token, user);
    }
}