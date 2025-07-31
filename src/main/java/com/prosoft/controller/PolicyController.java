package com.prosoft.controller;

import com.prosoft.model.Policy;
import com.prosoft.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/policies")
public class PolicyController {

    @Autowired
    private PolicyService policyService;

    @PostMapping("/create")
    public Policy createPolicy() {
        return policyService.createPolicy();
    }

    @PostMapping("/process1to2")
    public String processState1ToState2() {
        policyService.processAllState1ToState2();
        return "Обработка STATE1 → STATE2 завершена";
    }

    @PostMapping("/process2to3")
    public String processState2ToState3() {
        policyService.processAllState2ToState3();
        return "Обработка STATE2 → STATE3 завершена";
    }

    @GetMapping
    public List<Policy> getAllPolicies() {
        return policyService.getAllPolicies();
    }
}