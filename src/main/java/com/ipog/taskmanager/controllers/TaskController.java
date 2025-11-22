package com.ipog.taskmanager.controllers;

import com.ipog.taskmanager.models.TaskModel;
import com.ipog.taskmanager.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskModel>> getTasks() {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.findAll());
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Object> getTaskById(@PathVariable(value = "taskId") UUID taskId) {
        Optional<TaskModel> taskModelOptional = taskService.findById(taskId);
        if (!taskModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(taskModelOptional.get());
    }

    @PostMapping
    public ResponseEntity<Object> createTask(@RequestBody TaskModel taskModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.save(taskModel));
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Object> updateTask(@PathVariable(value = "taskId") UUID taskId, @RequestBody TaskModel taskModel) {
        Optional<TaskModel> taskModelOptional = taskService.findById(taskId);
        if (!taskModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found.");
        }
        var taskModel1 = taskModelOptional.get();
        taskModel1.setTitle(taskModel.getTitle());
        taskModel1.setDescription(taskModel.getDescription());
        return ResponseEntity.status(HttpStatus.OK).body(taskService.update(taskModel1));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Object> deleteTask(@PathVariable(value = "taskId") UUID taskId) {
        Optional<TaskModel> taskModelOptional = taskService.findById(taskId);
        if (!taskModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found.");
        }
        taskService.delete(taskModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Task deleted successfully.");
    }
}
