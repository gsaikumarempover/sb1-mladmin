package com.example.usermanagement.resolver;

import com.example.usermanagement.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AuthResolver {
    private final AuthService authService;
    
    @MutationMapping
    public AuthResponse login(@Argument String email, @Argument String password) {
        return authService.login(email, password);
    }
}