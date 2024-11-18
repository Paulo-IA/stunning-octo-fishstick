package com.taskflow.api.services;

import com.taskflow.api.domain.enterprise.Enterprise;
import com.taskflow.api.domain.task.Task;
import com.taskflow.api.domain.task.TaskRequestDTO;
import com.taskflow.api.domain.user.User;
import com.taskflow.api.enums.State;
import com.taskflow.api.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        User user = userService.findUserById(userId);
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
        newTask.setUser(user);
        newTask.setEnterprise(enterprise);

        this.taskRepository.save(newTask);

        return newTask;
    }

}
