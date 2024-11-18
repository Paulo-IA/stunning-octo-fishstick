package com.taskflow.api.services;

import com.taskflow.api.domain.enterprise.Enterprise;
import com.taskflow.api.domain.enterprise.EnterpriseRequestDTO;
import com.taskflow.api.repositories.EnterpriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EnterpriseService {
    @Autowired
    EnterpriseRepository enterpriseRepository;

    public Enterprise createEnterprise(EnterpriseRequestDTO data) {
        Enterprise newEnterprise = new Enterprise();

        newEnterprise.setName(data.name());

        this.enterpriseRepository.save(newEnterprise);

        return newEnterprise;
    }

    public Enterprise findEnterpriseById(UUID enterpriseId) {
        return enterpriseRepository.findById(enterpriseId).orElseThrow(() -> new IllegalArgumentException("Enterprise Not Registered"));
    }
}
