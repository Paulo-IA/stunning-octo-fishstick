package com.taskflow.api.domain.task;

import com.taskflow.api.enums.State;

import java.util.UUID;

public record TaskRequestDTO(String title,
                             String description,
                             Long startDate,
                             Long endDate,
                             State state) {
}
