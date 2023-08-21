package com.employee.service;

import com.employee.domain.Employee;
import com.employee.kafka.EmployeeEventProducer;
import com.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeEventProducer employeeEventProducer;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, EmployeeEventProducer employeeEventProducer) {
        this.employeeRepository = employeeRepository;
        this.employeeEventProducer = employeeEventProducer;
    }

    public Employee createEmployee(Employee employee) {
        Employee createdEmployee = employeeRepository.save(employee);
        employeeEventProducer.sendEmployeeCreatedEvent(createdEmployee.getUuid().toString());
        return createdEmployee;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployee(UUID uuid) {
        return employeeRepository.findById(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
    }

    public Employee updateEmployee(UUID uuid, Employee updatedEmployee) {
        Employee employee = employeeRepository.findById(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));

        employee.setEmail(updatedEmployee.getEmail());
        employee.setFullName(updatedEmployee.getFullName());
        employee.setBirthday(updatedEmployee.getBirthday());
        employee.setHobbies(updatedEmployee.getHobbies());

        employeeRepository.save(employee);
        employeeEventProducer.sendEmployeeUpdatedEvent(uuid.toString());
        return employee;
    }

    public void deleteEmployee(UUID uuid) {
        if (employeeRepository.existsById(uuid)) {
            employeeRepository.deleteById(uuid);
            employeeEventProducer.sendEmployeeDeletedEvent(uuid.toString());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
        }
    }
}
