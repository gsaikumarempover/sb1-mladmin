package com.example.usermanagement.resolver;

import com.example.usermanagement.entity.Employee;
import com.example.usermanagement.service.EmployeeService;
import com.example.usermanagement.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class EmployeeResolver {
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    
    @QueryMapping
    public Employee getEmployeeById(@Argument Long id) {
        return employeeService.getEmployeeById(id);
    }
    
    @QueryMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
    
    @QueryMapping
    public List<Employee> getActiveEmployees() {
        return employeeService.getActiveEmployees();
    }
    
    @QueryMapping
    public List<Employee> getEmployeesByDepartment(@Argument Long departmentId) {
        return employeeService.getEmployeesByDepartment(departmentId);
    }
    
    @MutationMapping
    public Employee createEmployee(@Argument EmployeeInput input) {
        Employee employee = new Employee();
        employee.setFirstName(input.getFirstName());
        employee.setLastName(input.getLastName());
        employee.setEmail(input.getEmail());
        employee.setEmployeeCode(input.getEmployeeCode());
        employee.setDepartment(departmentService.getDepartmentById(input.getDepartmentId()));
        employee.setIsActive(input.getIsActive());
        return employeeService.createEmployee(employee);
    }
    
    @MutationMapping
    public Employee updateEmployee(@Argument Long id, @Argument EmployeeInput input) {
        Employee employee = new Employee();
        employee.setFirstName(input.getFirstName());
        employee.setLastName(input.getLastName());
        employee.setEmail(input.getEmail());
        employee.setEmployeeCode(input.getEmployeeCode());
        employee.setDepartment(departmentService.getDepartmentById(input.getDepartmentId()));
        employee.setIsActive(input.getIsActive());
        return employeeService.updateEmployee(id, employee);
    }
    
    @MutationMapping
    public boolean deleteEmployee(@Argument Long id) {
        return employeeService.deleteEmployee(id);
    }
}