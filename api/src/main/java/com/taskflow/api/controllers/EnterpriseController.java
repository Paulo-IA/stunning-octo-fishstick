package com.taskflow.api.controllers;

import com.taskflow.api.domain.enterprise.Enterprise;
import com.taskflow.api.domain.enterprise.EnterpriseRequestDTO;
import com.taskflow.api.domain.enterprise.EnterpriseResponseDTO;
import com.taskflow.api.services.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<EnterpriseResponseDTO>> getEnterprises(@RequestParam(defaultValue = "0") int page,
                                                                      @RequestParam(defaultValue = "20") int size) {
        List<EnterpriseResponseDTO> allEnterprises = this.enterpriseService.getEnterprises(page, size);

        return ResponseEntity.ok(allEnterprises);
    }
}
