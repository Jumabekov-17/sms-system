# SMS System — Microservice

SMS verification microservice for Kun.uz project (educational project).

## About
Kun.uz and SMS System are 2 separate services
connected via RestTemplate.
When a user registers, an SMS code is generated,
saved in a separate DB and used for verification.

## Technologies
- Java 17 | Spring Boot | Spring Security
- PostgreSQL | REST API | Maven

## Architecture
kun.uz (8081) ←——→ sms-system (8085)

## Getting Started
1. Clone the repository
2. Configure DB and run `mvn spring-boot:run`
