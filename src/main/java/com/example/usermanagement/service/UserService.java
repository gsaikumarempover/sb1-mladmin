package com.example.usermanagement.service;

import com.example.usermanagement.entity.User;
import com.example.usermanagement.repository.UserRepository;
import com.example.usermanagement.repository.UserTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserTypeRepository userTypeRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Cacheable(value = "users", key = "#id")
    public User getUserById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));
    }
    
    @Cacheable(value = "users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    @CacheEvict(value = "users", allEntries = true)
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    
    @CacheEvict(value = "users", key = "#id")
    public User updateUser(Long id, User userDetails) {
        User user = getUserById(id);
        user.setEmail(userDetails.getEmail());
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setUserType(userDetails.getUserType());
        return userRepository.save(user);
    }
    
    @CacheEvict(value = "users", allEntries = true)
    public boolean deleteUser(Long id) {
        userRepository.deleteById(id);
        return true;
    }
}