# Web-Shop Application

This project is a web-shop application built using Angular for the frontend and Spring Boot for the backend. It leverages MongoDB for data storage and follows a Domain-Driven Design (DDD) approach.

## Project Structure

### Backend

- **Framework**: Spring Boot
- **Database**: MongoDB (for persistent storage), H2 (for in-memory storage)
- **Architecture**: Layered approach with separate DTOs for requests and responses
- **Main Entities**:
    - Product
    - Order
    - OrderItem
    - Cart
    - Shop

Swagger available at http://localhost:8080/swagger-ui/index.html