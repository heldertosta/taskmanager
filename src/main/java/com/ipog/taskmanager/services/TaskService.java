package com.ipog.taskmanager.services;

import com.ipog.taskmanager.models.TaskModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {

    List<TaskModel> findAll();

    Optional<TaskModel> findById(UUID id);

}