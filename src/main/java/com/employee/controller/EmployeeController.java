package com.employee.controller;


import com.employee.domain.Employee;
import com.employee.repository.EmployeeRepository;
import com.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    private final EmployeeService employeeService;


    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
        Employee createdEmployee = employeeService.createEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
    }


    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Employee> getEmployee(@PathVariable UUID uuid) {
        Employee employee = employeeRepository.findById(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<String> updateEmployee(@PathVariable UUID uuid, @Valid @RequestBody Employee updatedEmployee) {
        Employee employee = employeeRepository.findById(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));

        employee.setEmail(updatedEmployee.getEmail());
        employee.setFullName(updatedEmployee.getFullName());
        employee.setBirthday(updatedEmployee.getBirthday());
        employee.setHobbies(updatedEmployee.getHobbies());

        employeeRepository.save(employee);
        return ResponseEntity.ok("Employee updated successfully");
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<String> deleteEmployee(@PathVariable UUID uuid) {
        if (employeeRepository.existsById(uuid)) {
            employeeRepository.deleteById(uuid);
            return ResponseEntity.ok("Employee deleted successfully");
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
        }
    }
}
