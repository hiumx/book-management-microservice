# Book Management System with Microservice Architecture

## Introduction
The **Book Management System** is a scalable, modular application designed to manage books, authors, and related data using a microservice architecture. This approach allows for independent scaling, deployment, and management of various components, making it suitable for high-availability and high-performance systems.

## Features
- **Book Service**: Manage book information (title, author, genre, etc.)
- **Author Service**: Manage author details
- **User Service**: Manage users who interact with the system
- **Order Service**: Handle book orders and purchases
- **Search Service**: Advanced search capabilities for books and authors
- **Notification Service**: Send notifications about orders, updates, or promotions

## Technologies
- **Backend**: Java, Spring Boot, Spring Cloud, Spring Data
- **Database**: MySQL, MongoDB, Neo4j
- **Messaging**: Apache Kafka
- **API Gateway**: Spring Cloud Gateway
- **Service Discovery**: Eureka, Consul
- **Load Balancing**: NGINX
- **Authentication**: JWT, OAuth2
- **Containerization**: Docker, Docker Compose
- **Orchestration**: Kubernetes

## Architecture Overview
The system follows a **microservice architecture** where each service is responsible for a specific set of tasks. Services communicate via REST APIs or messaging queues (RabbitMQ/Kafka). The architecture ensures that each service can be developed, deployed, and scaled independently.

### Microservices Diagram
```plaintext
+---------------------+       +---------------------+       +---------------------+
|   API Gateway       | <---> |   Discovery Service | <---> |    Config Server    |
+---------------------+       +---------------------+       +---------------------+
        |                           |                            |
        v                           v                            v
+----------------+      +-------------------+       +----------------------+
|  Book Service  |      |   User Service   |       |   Author Service     |
+----------------+      +-------------------+       +----------------------+
        |                           |                            |
        v                           v                            v
+--------------------+     +--------------------+      +--------------------+
|    Order Service   |     |  Notification Svc  |      |    Search Service  |
+--------------------+     +--------------------+      +--------------------+
