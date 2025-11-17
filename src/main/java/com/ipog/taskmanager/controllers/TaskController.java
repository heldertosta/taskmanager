package com.ipog.taskmanager.controllers;

import com.ipog.taskmanager.models.TaskModel;
import com.ipog.taskmanager.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskModel>> getTasks() {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.findAll());
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Object> getTaskById(@PathVariable(value = "taskId") UUID taskId) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.findById(taskId).get());
    }

    @PostMapping
    public ResponseEntity<Object> createTask(@RequestBody TaskModel taskModel) {
        return null;
    }

    @PutMapping
    public ResponseEntity<Object> updateTask(@RequestBody TaskModel taskModel) {
        return null;
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteTask(@RequestBody TaskModel taskModel) {
        return null;
    }
}
