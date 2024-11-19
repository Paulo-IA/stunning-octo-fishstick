package com.taskflow.api.domain.enterprise;

import java.util.UUID;

public record EnterpriseResponseDTO(UUID enterpriseId,
                                    String name) {
}
