package com.employee.controller;


import com.employee.domain.Employee;
import com.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{uuid}")
    public Employee getEmployee(@PathVariable UUID uuid) {
        return employeeService.getEmployee(uuid);
    }

    @PutMapping("/{uuid}")
    public Employee updateEmployee(@PathVariable UUID uuid, @Valid @RequestBody Employee updatedEmployee) {
        return employeeService.updateEmployee(uuid, updatedEmployee);
    }

    @DeleteMapping("/{uuid}")
    public String deleteEmployee(@PathVariable UUID uuid) {
        employeeService.deleteEmployee(uuid);
        return "Employee deleted successfully";
    }
}
