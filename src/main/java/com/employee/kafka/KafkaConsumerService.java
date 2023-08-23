package com.employee.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

/*    @KafkaListener(topics = "employee-events", groupId = "employee-group")
    public void listenEmployeeEvents(String message) {
        if (message.contains("EmployeeCreated")) {
            System.out.println("Employee created: " + message);
        } else if (message.contains("EmployeeUpdated")) {
            System.out.println("Employee updated: " + message);
        } else if (message.contains("EmployeeDeleted")) {
            System.out.println("Employee deleted: " + message);
        }
    }*/
}
