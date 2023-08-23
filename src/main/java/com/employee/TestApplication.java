package com.employee;

import com.employee.kafka.EmployeeEventConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestApplication {

    public static void main(String[] args) {

        SpringApplication.run(TestApplication.class, args);
     /*   String bootstrapServers = "localhost:9092";
        String groupId = "employee-consumer-group";
        String topic = "employee-events";

        EmployeeEventConsumer consumer = new EmployeeEventConsumer(bootstrapServers, groupId, topic);
        consumer.consumeMessages();*/
    }

}
