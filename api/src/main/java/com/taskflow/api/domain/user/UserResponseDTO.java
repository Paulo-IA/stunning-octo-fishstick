package com.taskflow.api.domain.user;

import java.util.UUID;

public record UserResponseDTO(UUID userId,
                              String name,
                              String imgUrl) {
}
