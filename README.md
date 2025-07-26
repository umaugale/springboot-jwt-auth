# Spring Boot JWT Authentication

A simple **JWT-based authentication API** using **Spring Boot**, **Spring Security**, and **PostgreSQL**.

## Features
- User registration & login
- Password encryption with BCrypt
- JWT token generation & validation
- Secured endpoints (stateless session)
- DTO ↔ Entity mapping with MapStruct

## Endpoints
- `POST /auth/register` – Register new users
- `POST /auth/login` – Authenticate and get JWT

## Quick Start
1. Clone the repo  
2. Configure `application.properties` with your PostgreSQL credentials  
3. Run:
