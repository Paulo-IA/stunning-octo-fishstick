package com.taskflow.api.controllers;

import com.taskflow.api.domain.task.Task;
import com.taskflow.api.domain.task.TaskRequestDTO;
import com.taskflow.api.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    @Autowired
    TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody TaskRequestDTO body,
                                           @RequestParam UUID userId,
                                           @RequestParam UUID enterpriseId) {
        Task task = taskService.createTask(body, userId, enterpriseId);

        return ResponseEntity.ok(task);
    }

}
