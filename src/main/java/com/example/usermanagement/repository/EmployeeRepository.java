package com.example.usermanagement.repository;

import com.example.usermanagement.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByIsActiveTrue();
    List<Employee> findByDepartmentId(Long departmentId);
}