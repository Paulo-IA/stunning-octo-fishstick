package com.taskflow.api.controllers;

import com.taskflow.api.domain.task.Task;
import com.taskflow.api.domain.task.TaskRequestDTO;
import com.taskflow.api.domain.task.TaskResponseDTO;
import com.taskflow.api.enums.State;
import com.taskflow.api.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    @Autowired
    TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody TaskRequestDTO body,
                                           @RequestParam(required = false) UUID userId,
                                           @RequestParam UUID enterpriseId) {
        Task task = taskService.createTask(body, userId, enterpriseId);

        return ResponseEntity.ok(task);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> getTasks(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "20") int size,
                                                          @RequestParam(required = false) String title,
                                                          @RequestParam(required = false) String description,
                                                          @RequestParam(required = false) Long startDate,
                                                          @RequestParam(required = false) Long endDate,
                                                          @RequestParam(required = false) State state,
                                                          @RequestParam(required = false) UUID enterpriseId) {
        List<TaskResponseDTO> allTasks = this.taskService.getTasks(page, size, title, description, startDate, endDate, state, enterpriseId);

        return ResponseEntity.ok(allTasks);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TaskResponseDTO>> getTasksByUser(@PathVariable UUID userId,
                                                                @RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "20") int size) {
        List<TaskResponseDTO> allTasks = this.taskService.getTasksByUser(userId, page, size);

        return ResponseEntity.ok(allTasks);
    }

}
