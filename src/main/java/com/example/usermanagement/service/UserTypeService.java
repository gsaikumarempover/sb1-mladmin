package com.example.usermanagement.service;

import com.example.usermanagement.entity.UserType;
import com.example.usermanagement.repository.UserTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserTypeService {
    private final UserTypeRepository userTypeRepository;
    
    @Cacheable(value = "userTypes", key = "#id")
    public UserType getUserTypeById(Long id) {
        return userTypeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("UserType not found"));
    }
    
    @Cacheable(value = "userTypes")
    public List<UserType> getAllUserTypes() {
        return userTypeRepository.findAll();
    }
    
    @CacheEvict(value = "userTypes", allEntries = true)
    public UserType createUserType(UserTypeInput input) {
        UserType userType = new UserType();
        userType.setName(input.getName());
        userType.setDescription(input.getDescription());
        return userTypeRepository.save(userType);
    }
    
    @CacheEvict(value = "userTypes", key = "#id")
    public UserType updateUserType(Long id, UserTypeInput input) {
        UserType userType = getUserTypeById(id);
        userType.setName(input.getName());
        userType.setDescription(input.getDescription());
        return userTypeRepository.save(userType);
    }
    
    @CacheEvict(value = "userTypes", allEntries = true)
    public boolean deleteUserType(Long id) {
        userTypeRepository.deleteById(id);
        return true;
    }
}