# Customer & Deals Management API

---

## Overview

A RESTful Spring Boot API for managing **Customers** and their business **Deals**.


This project provides:
- CRUD
- Bidirectional Relationship
- Error Validation and structured error messages
- PostgreSQL auto-schema generation

### Stack

- Java 17 / Springboot 3.x
- PostgreSQL
- Hibernate JPA
- Maven

---

## API Endpoints

### Customer

- `GET /customers`  → List all customers
- `GET /customers/{id}`  → Find a customer by ID
- `POST /customers`  → Create a new Customer, which takes a `CustomerDto` in its body
- `PUT /customers/{id}`  → Update an existing customer, which takes a `CustomerDto` in its body
- `DELETE /customers/{id}`  → Delete a customer while also deleting all their deals

**Request Body Example (CustomerDto)**
```json
{
  "firstName": "Abe",
  "lastName": "Adams",
  "phoneNumber": "123456789012",
  "email": "generic@email.com",
  "city": "City"
}
```
- All fields are required
- `phoneNumber` must be between 10-11 digits (numbers only)
**Expected Response**
```json
{
  "id": 1,
  "firstName": "Abe",
  "lastName": "Adams",
  "city": "City",
  "deals": []
}
```
**Error Responses**
- 404 Not Found
```json
{
  "instant": "2025-10-23T16:00:00",
  "status": 404,
  "error": "Resource not Found",
  "errorMessages": [],
  "message": "Customer with id 999 not found!",
  "path": "/customers/999"
}
```
- 400 Validation Error
```json
{
  "instant": "2025-10-23T16:05:00",
  "status": 400,
  "error": "Missing parameters",
  "errorMessages": [
    { "field": "email", "message": "Email is required!" },
    { "field": "phoneNumber", "message": "Phone must be composed by digits, between 10 to 11 digits only!" }
  ],
  "message": "Obligatory parameters!",
  "path": "/customers"
}
```
**GET Customer by ID Example (with deals)**
```json
{
  "id": 1,
  "firstName": "Abe",
  "lastName": "Adams",
  "city": "City",
  "deals": [
    {
      "id": 1,
      "projectName": "API",
      "customerId": 1,
      "customerName": "Abe Adams"
    }
  ]
}
```
---
### Deals

- `GET /deals`  → List all deals
- `GET /deals/{id}`  → Find deals by ID
- `POST /deals`  → Create a new deal, takes a `DealsDto` in its body
- `PUT /deals/{id}`  → Update an existing deal,takes a `UpdateDealsDto` in its body
- `DELETE /deals/{id}`  → Delete a deal from a specific Customer

**Request Body (DealsDto)**

```json
    {
        "projectName": "API",
        "address": "Home",
        "details": "API Creation",
        "customerId": 1
    }
```
- The fields `projectName`, `address` and `customerId` are obligatory
- `projectName` is unique
**Request Body (UpdateDealsDto)**
```json
{
    "projectName" : "Another Change",
    "address": "Localhost",
    "details": "STILL REST API"
}
```
- The fields `projectName`, "address" are obligatory
- `projectName` must remain unique

**Expected Response**
```json
{
    "id": 1,
    "projectName": "API",
    "customerId": 1,
    "customerName": "Abe Adams"
}
```
**Error Responses**
- 404 Not Found
```json
{
  "instant": "2025-10-23T16:15:00",
  "status": 404,
  "error": "Resource not Found",
  "errorMessages": [],
  "message": "Deal with id 999 not found!",
  "path": "/deals/999"
}
```
- 400 Validation Error
```json
{
  "instant": "2025-10-23T16:20:00",
  "status": 400,
  "error": "Missing parameters",
  "errorMessages": [
    { "field": "projectName", "message": "Project Name is required!" },
    { "field": "address", "message": "Address is required!" }
  ],
  "message": "Obligatory parameters!",
  "path": "/deals"
}
```
- 409 Conflict (Duplicate Project Name)
```json
{
  "instant": "2025-10-23T16:25:00",
  "status": 409,
  "error": "Conflict",
  "errorMessages": [],
  "message": "Project name 'API' is already taken!",
  "path": "/deals"
}
```
