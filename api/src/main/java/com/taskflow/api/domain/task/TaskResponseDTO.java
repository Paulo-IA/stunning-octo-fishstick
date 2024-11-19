package com.taskflow.api.domain.task;

import com.taskflow.api.domain.enterprise.Enterprise;
import com.taskflow.api.domain.user.User;
import com.taskflow.api.enums.State;

import java.util.Date;
import java.util.UUID;

public record TaskResponseDTO(UUID id,
                              String title,
                              String description,
                              Date startDate,
                              Date endDate,
                              State state,
                              User user,
                              Enterprise enterprise) {
}
