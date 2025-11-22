package com.ipog.taskmanager.controllers;

import com.ipog.taskmanager.models.TaskModel;
import com.ipog.taskmanager.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tarefas")
@Tag(name = "Tarefas", description = "Endpoints para gerenciamento de tarefas")
public class TaskController {

    private static final String TASK_NOT_FOUND_MESSAGE = "Tarefa não encontrada.";

    final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Operation(summary = "Listar todas as tarefas", description = "Lista todas as tarefas cadastradas no sistema")
    @GetMapping
    public ResponseEntity<List<TaskModel>> getTasks() {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.findAll());
    }

    @Operation(summary = "Buscar tarefa pelo ID", description = "Retorna uma tarefa específica pelo seu identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    @GetMapping("/{taskId}")
    public ResponseEntity<Object> getTaskById(@PathVariable(value = "taskId") UUID taskId) {
        Optional<TaskModel> taskModelOptional = taskService.findById(taskId);
        if (taskModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(TASK_NOT_FOUND_MESSAGE);
        }
        return ResponseEntity.status(HttpStatus.OK).body(taskModelOptional.get());
    }

    @Operation(summary = "Criar uma nova tarefa", description = "Criar uma nova tarefa no sistema com título e descrição")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tarefa criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos")
    })
    @PostMapping
    public ResponseEntity<Object> createTask(@RequestBody @Valid TaskModel taskModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.save(taskModel));
    }

    @Operation(summary = "Atualizar tarefa", description = "Atualizar uma tarefa existente pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada"),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos")
    })
    @PutMapping("/{taskId}")
    public ResponseEntity<Object> updateTask(@PathVariable(value = "taskId") UUID taskId,
            @RequestBody @Valid TaskModel taskModel) {
        Optional<TaskModel> taskModelOptional = taskService.findById(taskId);
        if (taskModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(TASK_NOT_FOUND_MESSAGE);
        }
        var taskModel1 = taskModelOptional.get();
        taskModel1.setTitle(taskModel.getTitle());
        taskModel1.setDescription(taskModel.getDescription());
        return ResponseEntity.status(HttpStatus.OK).body(taskService.update(taskModel1));
    }

    @Operation(summary = "Excluir uma tarefa", description = "Excluir uma tarefa pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Object> deleteTask(@PathVariable(value = "taskId") UUID taskId) {
        Optional<TaskModel> taskModelOptional = taskService.findById(taskId);
        if (taskModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(TASK_NOT_FOUND_MESSAGE);
        }
        taskService.delete(taskModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Tarefa excluída com sucesso.");
    }
}
