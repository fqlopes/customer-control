# API de Gestão de Clientes e Negócios


---
[Read in English](README.md)
## Visão Geral

Uma API RESTful em Spring Boot para gerenciar **Clientes** e seus **Negócios (Deals)**.

Este projeto fornece:
- Operações CRUD
- Relacionamento bidirecional entre Clientes e Negócios
- Validação de dados com mensagens de erro estruturadas
- Geração automática de schema

### Stack

- Java 17 / Spring Boot 3.x
- PostgreSQL
- Hibernate JPA
- Maven

---

## Endpoints da API

### Cliente

- `GET /customers`  → Lista todos os clientes
- `GET /customers/{id}`  → Busca um cliente pelo ID
- `POST /customers`  → Cria um novo cliente, recebendo um `CustomerDto` no corpo
- `PUT /customers/{id}`  → Atualiza um cliente existente, recebendo um `CustomerDto` no corpo
- `DELETE /customers/{id}`  → Deleta um cliente e todos os seus negócios

**Exemplo de Requisição (CustomerDto)**
```json
{
  "firstName": "Abe",
  "lastName": "Adams",
  "phoneNumber": "123456789012",
  "email": "generic@email.com",
  "city": "Cidade"
}
```
- Todos os campos são obrigatórios
- `phoneNumber` deve ter entre 10 e 11 dígitos (apenas números)

**Exemplo de Resposta**
```json
{
  "id": 1,
  "firstName": "Abe",
  "lastName": "Adams",
  "city": "Cidade",
  "deals": []
}
```

**Respostas de Erro**
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
- 400 Erro de Validação
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

**Exemplo de GET Cliente por ID (com negócios)**
```json
{
  "id": 1,
  "firstName": "Abe",
  "lastName": "Adams",
  "city": "Cidade",
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
### Negócios (Deals)

- `GET /deals`  → Lista todos os negócios
- `GET /deals/{id}`  → Busca um negócio pelo ID
- `POST /deals`  → Cria um novo negócio, recebendo um `DealsDto` no corpo
- `PUT /deals/{id}`  → Atualiza um negócio existente, recebendo um `UpdateDealsDto` no corpo
- `DELETE /deals/{id}`  → Deleta um negócio de um cliente específico

**Exemplo de Requisição (DealsDto)**
```json
{
  "projectName": "API",
  "address": "Home",
  "details": "Criação da API",
  "customerId": 1
}
```
- Campos obrigatórios: `projectName`, `address` e `customerId`
- `projectName` deve ser único

**Exemplo de Requisição para Atualização (UpdateDealsDto)**
```json
{
  "projectName" : "Outra Alteração",
  "address": "Localhost",
  "details": "Ainda REST API"
}
```
- Campos obrigatórios: `projectName` e `address`
- `projectName` deve permanecer único

**Exemplo de Resposta**
```json
{
  "id": 1,
  "projectName": "API",
  "customerId": 1,
  "customerName": "Abe Adams"
}
```

**Respostas de Erro**
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
- 400 Erro de Validação
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
- 409 Conflito (Nome de projeto duplicado)
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

