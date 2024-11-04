package com.example.usermanagement.service;

import com.example.usermanagement.entity.Department;
import com.example.usermanagement.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    
    @Cacheable(value = "departments", key = "#id")
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Department not found"));
    }
    
    @Cacheable(value = "departments")
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }
    
    @Cacheable(value = "departments")
    public List<Department> getActiveDepartments() {
        return departmentRepository.findByIsActiveTrue();
    }
    
    @CacheEvict(value = "departments", allEntries = true)
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }
    
    @CacheEvict(value = "departments", allEntries = true)
    public Department updateDepartment(Long id, Department departmentDetails) {
        Department department = getDepartmentById(id);
        department.setName(departmentDetails.getName());
        department.setCode(departmentDetails.getCode());
        department.setDescription(departmentDetails.getDescription());
        department.setIsActive(departmentDetails.getIsActive());
        return departmentRepository.save(department);
    }
    
    @CacheEvict(value = "departments", allEntries = true)
    public boolean deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
        return true;
    }
}