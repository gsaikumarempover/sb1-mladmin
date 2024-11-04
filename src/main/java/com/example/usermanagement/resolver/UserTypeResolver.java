package com.example.usermanagement.resolver;

import com.example.usermanagement.entity.UserType;
import com.example.usermanagement.service.UserTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserTypeResolver {
    private final UserTypeService userTypeService;
    
    @QueryMapping
    public UserType getUserTypeById(@Argument Long id) {
        return userTypeService.getUserTypeById(id);
    }
    
    @QueryMapping
    public List<UserType> getAllUserTypes() {
        return userTypeService.getAllUserTypes();
    }
    
    @MutationMapping
    public UserType createUserType(@Argument UserTypeInput input) {
        return userTypeService.createUserType(input);
    }
    
    @MutationMapping
    public UserType updateUserType(@Argument Long id, @Argument UserTypeInput input) {
        return userTypeService.updateUserType(id, input);
    }
    
    @MutationMapping
    public boolean deleteUserType(@Argument Long id) {
        return userTypeService.deleteUserType(id);
    }
}