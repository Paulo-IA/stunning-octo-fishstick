package com.taskflow.api.controllers;

import com.taskflow.api.domain.enterprise.Enterprise;
import com.taskflow.api.domain.enterprise.EnterpriseRequestDTO;
import com.taskflow.api.services.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/enterprise")
public class EnterpriseController {
    @Autowired
    EnterpriseService enterpriseService;

    @PostMapping
    public ResponseEntity<Enterprise> createEnterprise(@RequestBody EnterpriseRequestDTO body) {
        Enterprise enterprise = enterpriseService.createEnterprise(body);

        return ResponseEntity.ok(enterprise);
    }
}
