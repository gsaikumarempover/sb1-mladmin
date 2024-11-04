package com.example.usermanagement.resolver;

import com.example.usermanagement.entity.User;
import com.example.usermanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserResolver {
    private final UserService userService;
    
    @QueryMapping
    public User getUserById(@Argument Long id) {
        return userService.getUserById(id);
    }
    
    @QueryMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    
    @QueryMapping
    public List<User> getUsersByType(@Argument Long typeId) {
        return userService.getUsersByType(typeId);
    }
    
    @MutationMapping
    public User createUser(@Argument UserInput input) {
        return userService.createUser(input);
    }
    
    @MutationMapping
    public User updateUser(@Argument Long id, @Argument UserInput input) {
        return userService.updateUser(id, input);
    }
    
    @MutationMapping
    public boolean deleteUser(@Argument Long id) {
        return userService.deleteUser(id);
    }
}