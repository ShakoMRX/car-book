# Car Book Application

## Overview

The `Car Book` application is a Spring Boot-based project designed to manage car bookings with a variety of features to support development, testing, and security.

### Key Features:

- **Spring Boot Version:** 3.3.2
- **Java Version:** 17
- **In-Memory Database:** H2
  - **JDBC URL:** `jdbc:h2:mem:testdb`
  - **Username:** `shako`
  - **Password:** (no password set)
- **Database Migrations:** Managed with Flyway
  - **Migration Scripts Location:** `classpath:db/migration`
  - **Flyway Configuration:**
    - `spring.flyway.enabled=true`
    - `spring.flyway.baseline-on-migrate=true`
- **RESTful API Development:**
  - **Spring Boot Starters:**
    - `spring-boot-starter-web`: For creating web applications, including RESTful services.
    - `spring-boot-starter-data-jpa`: For working with relational databases using JPA.
    - `spring-boot-starter-security`: For securing the application.
- **JWT Authentication:**
  - **Token Validity:** 1 hour (`jwt.tokenValidity=3600000`)
- **Interactive API Documentation:**
  - **Swagger UI Path:** `/swagger-ui.html`
- **H2 Console:**
  - **Console Path:** `/h2-console`
  - **Access URL:** [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
- **API Documentation URL:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

This setup provides a solid foundation for building and testing a secure, RESTful web application with database management capabilities.

## Getting Started

### Running the Application

To run the application, use the following command:

```bash
./gradlew bootRun
