Copy code
# Employee Management System

## Overview

This project provides a RESTful API for managing employees in a company. It includes endpoints for creating, reading, updating, and deleting employee records. The project also integrates Kafka for event notifications related to employee actions.

## Features

- Create employees with unique UUIDs.
- Retrieve a list of all employees.
- Get detailed employee information by UUID.
- Update employee details.
- Delete employees.
- Kafka integration for event notifications on employee actions.

## Technologies

- Java 17
- Spring Boot
- Spring Data JPA
- H2 Database
- Spring Security
- Kafka

## Getting Started

### Prerequisites

- Java 17 JDK
- Apache Maven
- Docker (for Kafka)

### Installation

1. Clone the repository:

git clone https://github.com/your-username/employee-management.git

2. Build and run the application:

cd employee-management
mvn spring-boot:run


3. Start Kafka (if needed):
docker-compose -f kafka-docker-compose.yml up -d

**Usage**
**API Documentation**
Access the API documentation using Swagger UI:

Open your web browser and navigate to http://localhost:8080/swagger-ui.html

**Authentication**
For certain endpoints, basic authentication is required. Default credentials:

Username: admin
Password: admin
Configure these settings in application.properties.
**Running Kafka Consumer**
docker-compose -f kafka-docker-compose.yml up -d

**Run the Kafka consumer:**
java -jar employee-consumer.jar
