package com.example.usermanagement.repository;

import com.example.usermanagement.entity.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTypeRepository extends JpaRepository<UserType, Long> {
}