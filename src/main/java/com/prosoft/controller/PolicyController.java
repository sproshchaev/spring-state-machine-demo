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
    public String process1to2() {
        policyService.processState1ToState2();
        return "Processed STATE1 to STATE2";
    }

    @PostMapping("/process2to3")
    public String process2to3() {
        policyService.processState2ToState3();
        return "Processed STATE2 to STATE3";
    }

    @GetMapping
    public List<Policy> getAllPolicies() {
        return policyService.getAllPolicies();
    }
}