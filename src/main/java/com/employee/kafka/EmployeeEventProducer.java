package com.employee.kafka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class EmployeeEventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public EmployeeEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEmployeeCreatedEvent(String employeeId) {
        sendEvent(employeeId, "EmployeeCreated");
    }

    public void sendEmployeeUpdatedEvent(String employeeId) {
        sendEvent(employeeId, "EmployeeUpdated");
    }

    public void sendEmployeeDeletedEvent(String employeeId) {
        sendEvent(employeeId, "EmployeeDeleted");
    }

    private void sendEvent(String employeeId, String eventType) {
        kafkaTemplate.send("employee-events", eventType, employeeId);
    }
}
