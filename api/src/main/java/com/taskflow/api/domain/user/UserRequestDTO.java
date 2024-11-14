package com.taskflow.api.domain.user;

import org.springframework.web.multipart.MultipartFile;

public record UserRequestDTO(String name, String email, MultipartFile image) {
}
