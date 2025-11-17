package com.ipog.taskmanager.services.impl;

import com.ipog.taskmanager.models.TaskModel;
import com.ipog.taskmanager.repositories.TaskRepository;
import com.ipog.taskmanager.services.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<TaskModel> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Optional<TaskModel> findById(UUID taskId) {
        return taskRepository.findById(taskId);
    }
}