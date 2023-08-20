package com.employee.service;

import com.employee.domain.Employee;
import com.employee.exception.EmailAlreadyExistsException;
import com.employee.kafka.EmployeeEvent;
import com.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private KafkaTemplate<String, EmployeeEvent> kafkaTemplate;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    public Employee createEmployee(Employee employee) {
        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new EmailAlreadyExistsException(employee.getEmail());
        }
        Employee em= employeeRepository.save(employee);
        EmployeeEvent event = new EmployeeEvent("created", em);
        kafkaTemplate.send("employee-events", event);
        return em;
    }

}
