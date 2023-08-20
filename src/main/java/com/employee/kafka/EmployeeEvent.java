package com.employee.kafka;

import com.employee.domain.Employee;
import lombok.Setter;

@Setter
public class EmployeeEvent {
    private String eventType;
    private Employee employee;

    public EmployeeEvent(String message, Employee em) {

    }
}
