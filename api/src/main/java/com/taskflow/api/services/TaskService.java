package com.taskflow.api.services;

import com.taskflow.api.domain.enterprise.Enterprise;
import com.taskflow.api.domain.task.Task;
import com.taskflow.api.domain.task.TaskRequestDTO;
import com.taskflow.api.domain.task.TaskResponseDTO;
import com.taskflow.api.domain.user.User;
import com.taskflow.api.enums.State;
import com.taskflow.api.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserService userService;

    @Autowired
    EnterpriseService enterpriseService;

    public Task createTask(TaskRequestDTO data, UUID userId, UUID enterpriseId) {
        Enterprise enterprise = enterpriseService.findEnterpriseById(enterpriseId);

        Date startDate = new Date(data.startDate());
        Date endDate = new Date(data.endDate());

        if (startDate.after(endDate)) {
            Date aux_startDate = startDate;

            startDate = endDate;
            endDate = aux_startDate;
        }

        Task newTask = new Task();

        newTask.setTitle(data.title());
        newTask.setDescription(data.description());
        newTask.setStart_date(startDate);
        newTask.setEnd_date(endDate);
        newTask.setState(data.state() != null ? data.state() : State.NOT_STARTED);
        newTask.setUser(userId != null ? userService.findUserById(userId) : null);
        newTask.setEnterprise(enterprise);

        this.taskRepository.save(newTask);

        return newTask;
    }

    public List<TaskResponseDTO> getTasks(int page,
                                          int size,
                                          String title,
                                          String description,
                                          Long startDate,
                                          Long endDate,
                                          State state,
                                          UUID enterpriseId) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Task> taskPage = this.taskRepository
                .findAllFilteredTasks(
                        title,
                        description,
                        startDate != null ? new Date(startDate): null,
                        endDate != null ? new Date(endDate): null,
                        state != null ? state : null,
                        enterpriseId,
                        pageable
                );

        return taskPage.map(task -> new TaskResponseDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStart_date(),
                task.getEnd_date(),
                task.getState(),
                task.getUser(),
                task.getEnterprise()
        )).toList();
    }

    public List<TaskResponseDTO> getTasksByUser(UUID userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Task> taskPage = this.taskRepository.findByUserId(userId, pageable);

        return taskPage.map(task -> new TaskResponseDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStart_date(),
                task.getEnd_date(),
                task.getState(),
                task.getUser(),
                task.getEnterprise()
        )).stream().toList();
    }

}