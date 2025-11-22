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

### 1. Listar todas as tarefas
-   **Método:** `GET`
-   **URL:** `/tasks`
-   **Resposta de Sucesso (200 OK):**
    ```json
    [
      {
        "id": "uuid",
        "title": "Título da Tarefa",
        "description": "Descrição da Tarefa"
      }
    ]
    ```

### 2. Buscar tarefa por ID
-   **Método:** `GET`
-   **URL:** `/tasks/{id}`
-   **Resposta de Sucesso (200 OK):**
    ```json
    {
      "id": "uuid",
      "title": "Título da Tarefa",
      "description": "Descrição da Tarefa"
    }
    ```
-   **Resposta de Erro (404 Not Found):**
    ```
    Task not found.
    ```

### 3. Criar nova tarefa
-   **Método:** `POST`
-   **URL:** `/tasks`
-   **Corpo da Requisição (JSON):**
    ```json
    {
      "title": "Título da Tarefa",
      "description": "Descrição da Tarefa"
    }
    ```
-   **Resposta de Sucesso (201 Created):**
    ```json
    {
      "id": "uuid",
      "title": "Título da Tarefa",
      "description": "Descrição da Tarefa"
    }
    ```

### 4. Atualizar tarefa existente
-   **Método:** `PUT`
-   **URL:** `/tasks/{id}`
-   **Corpo da Requisição (JSON):**
    ```json
    {
      "title": "Novo Título",
      "description": "Nova Descrição"
    }
    ```
-   **Resposta de Sucesso (200 OK):**
    ```json
    {
      "id": "uuid",
      "title": "Novo Título",
      "description": "Nova Descrição"
    }
    ```
-   **Resposta de Erro (404 Not Found):**
    ```
    Task not found.
    ```

### 5. Excluir tarefa
-   **Método:** `DELETE`
-   **URL:** `/tasks/{id}`
-   **Resposta de Sucesso (200 OK):**
    ```
    Task deleted successfully.
    ```
-   **Resposta de Erro (404 Not Found):**
    ```
    Task not found.
    ```
