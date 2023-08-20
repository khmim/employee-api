package com.employee.service;

import com.employee.domain.Employee;
import com.employee.exception.EmailAlreadyExistsException;
import com.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    public Employee createEmployee(Employee employee) {
        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new EmailAlreadyExistsException(employee.getEmail());
        }
        return employeeRepository.save(employee);
    }

}
