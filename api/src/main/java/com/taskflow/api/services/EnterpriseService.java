package com.taskflow.api.services;

import com.taskflow.api.domain.enterprise.Enterprise;
import com.taskflow.api.domain.enterprise.EnterpriseRequestDTO;
import com.taskflow.api.domain.enterprise.EnterpriseResponseDTO;
import com.taskflow.api.repositories.EnterpriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<EnterpriseResponseDTO> getEnterprises(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Enterprise> enterprisesPage = this.enterpriseRepository.findAll(pageable);

        return enterprisesPage.map(enterprise -> new EnterpriseResponseDTO(
                enterprise.getId(),
                enterprise.getName()
        )).toList();
    }
}
