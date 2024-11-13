package com.taskflow.api.repositories;

import com.taskflow.api.domain.enterprise.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EnterpriseRepository extends JpaRepository<Enterprise, UUID>  {
}
