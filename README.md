# Task Manager API

Este projeto é uma API RESTful para gerenciamento de tarefas pessoais, desenvolvida como parte de uma atividade acadêmica.

## Tecnologias Utilizadas

- Java 21
- Spring Boot 3.5.7
- Spring Data JPA
- H2 Database (Banco de dados em memória)
- Maven

## Configuração e Execução

1.  Certifique-se de ter o Java 21 instalado.
2.  Clone o repositório.
3.  Navegue até a pasta do projeto.
4.  Execute o comando:
    ```bash
    ./mvnw spring-boot:run
    ```
5.  A API estará disponível em `http://localhost:8090`.

## Endpoints da API

### API Documentation

The API documentation is available via Swagger UI:
- **URL:** `http://localhost:8080/swagger-ui/index.html`

## API Endpoints (v1)

Base URL: `/api/v1/tarefas`

### 1. List All Tasks
- **URL:** `/api/v1/tarefas`
- **Method:** `GET`
- **Response:** `200 OK`
  ```json
  [
    {
      "id": "uuid",
      "title": "Task Title",
      "description": "Task Description"
    }
  ]
  ```

### 2. Get Task by ID
- **URL:** `/api/v1/tarefas/{id}`
- **Method:** `GET`
- **Response:** `200 OK` or `404 Not Found`

### 3. Create Task
- **URL:** `/api/v1/tarefas`
- **Method:** `POST`
- **Body:**
  ```json
  {
    "title": "Task Title",
    "description": "Task Description"
  }
  ```
- **Response:** `201 Created` or `400 Bad Request` (Validation Error)

### 4. Update Task
- **URL:** `/api/v1/tarefas/{id}`
- **Method:** `PUT`
- **Body:**
  ```json
  {
    "title": "Updated Title",
    "description": "Updated Description"
  }
  ```
- **Response:** `200 OK` or `404 Not Found` or `400 Bad Request`

### 5. Delete Task
- **URL:** `/api/v1/tarefas/{id}`
- **Method:** `DELETE`
- **Response:** `200 OK` or `404 Not Found`

## Error Handling

The API returns standard error responses:

```json
{
  "timestamp": "2023-10-27T10:00:00",
  "status": 400,
  "error": "Validation Error",
  "message": "{title=Title is mandatory}",
  "path": "/api/v1/tarefas"
}
```
