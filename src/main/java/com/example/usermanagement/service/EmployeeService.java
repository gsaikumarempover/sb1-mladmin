package com.example.usermanagement.service;

import com.example.usermanagement.entity.Employee;
import com.example.usermanagement.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    
    @Cacheable(value = "employees", key = "#id")
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Employee not found"));
    }
    
    @Cacheable(value = "employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    
    @Cacheable(value = "employees")
    public List<Employee> getActiveEmployees() {
        return employeeRepository.findByIsActiveTrue();
    }
    
    @Cacheable(value = "employees")
    public List<Employee> getEmployeesByDepartment(Long departmentId) {
        return employeeRepository.findByDepartmentId(departmentId);
    }
    
    @CacheEvict(value = "employees", allEntries = true)
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
    
    @CacheEvict(value = "employees", allEntries = true)
    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Employee employee = getEmployeeById(id);
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmail(employeeDetails.getEmail());
        employee.setEmployeeCode(employeeDetails.getEmployeeCode());
        employee.setDepartment(employeeDetails.getDepartment());
        employee.setIsActive(employeeDetails.getIsActive());
        return employeeRepository.save(employee);
    }
    
    @CacheEvict(value = "employees", allEntries = true)
    public boolean deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
        return true;
    }
}