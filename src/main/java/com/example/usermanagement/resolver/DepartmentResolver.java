package com.example.usermanagement.resolver;

import com.example.usermanagement.entity.Department;
import com.example.usermanagement.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class DepartmentResolver {
    private final DepartmentService departmentService;
    
    @QueryMapping
    public Department getDepartmentById(@Argument Long id) {
        return departmentService.getDepartmentById(id);
    }
    
    @QueryMapping
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }
    
    @QueryMapping
    public List<Department> getActiveDepartments() {
        return departmentService.getActiveDepartments();
    }
    
    @MutationMapping
    public Department createDepartment(@Argument DepartmentInput input) {
        Department department = new Department();
        department.setName(input.getName());
        department.setCode(input.getCode());
        department.setDescription(input.getDescription());
        department.setIsActive(input.getIsActive());
        return departmentService.createDepartment(department);
    }
    
    @MutationMapping
    public Department updateDepartment(@Argument Long id, @Argument DepartmentInput input) {
        Department department = new Department();
        department.setName(input.getName());
        department.setCode(input.getCode());
        department.setDescription(input.getDescription());
        department.setIsActive(input.getIsActive());
        return departmentService.updateDepartment(id, department);
    }
    
    @MutationMapping
    public boolean deleteDepartment(@Argument Long id) {
        return departmentService.deleteDepartment(id);
    }
}