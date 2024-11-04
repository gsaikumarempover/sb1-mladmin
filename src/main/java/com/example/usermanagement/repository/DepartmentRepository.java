package com.example.usermanagement.repository;

import com.example.usermanagement.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findByIsActiveTrue();
}