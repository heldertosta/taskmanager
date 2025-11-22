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
@Tag(name = "Tasks", description = "Endpoints for managing tasks")
public class TaskController {

    private static final String TASK_NOT_FOUND_MESSAGE = "Task not found.";

    final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Operation(summary = "List all tasks", description = "Retrieve a list of all registered tasks")
    @GetMapping
    public ResponseEntity<List<TaskModel>> getTasks() {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.findAll());
    }

    @Operation(summary = "Get task by ID", description = "Retrieve a specific task by its unique identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task found"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @GetMapping("/{taskId}")
    public ResponseEntity<Object> getTaskById(@PathVariable(value = "taskId") UUID taskId) {
        Optional<TaskModel> taskModelOptional = taskService.findById(taskId);
        if (taskModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(TASK_NOT_FOUND_MESSAGE);
        }
        return ResponseEntity.status(HttpStatus.OK).body(taskModelOptional.get());
    }

    @Operation(summary = "Create a new task", description = "Create a new task with title and description")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Task created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    public ResponseEntity<Object> createTask(@RequestBody @Valid TaskModel taskModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.save(taskModel));
    }

    @Operation(summary = "Update a task", description = "Update an existing task by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task updated successfully"),
            @ApiResponse(responseCode = "404", description = "Task not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
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

    @Operation(summary = "Delete a task", description = "Delete a task by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Object> deleteTask(@PathVariable(value = "taskId") UUID taskId) {
        Optional<TaskModel> taskModelOptional = taskService.findById(taskId);
        if (taskModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(TASK_NOT_FOUND_MESSAGE);
        }
        taskService.delete(taskModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Task deleted successfully.");
    }
}
