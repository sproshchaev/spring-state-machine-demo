package com.prosoft.service;

import com.prosoft.Repository.PolicyRepository;
import com.prosoft.model.Policy;
import com.prosoft.model.PolicyEvents;
import com.prosoft.model.PolicyState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class PolicyService {

    @Autowired
    private PolicyRepository policyRepository;

    @Autowired
    private PolicyStateMachineService stateMachineService;

    public Policy createPolicy() {
        String id = "POLICY_" + System.currentTimeMillis();
        Policy policy = new Policy(id);
        policyRepository.save(policy);
        System.out.println("Создан новый полис: " + policy);
        return policy;
    }

    public void processAllState1ToState2() {
        List<Policy> policies = policyRepository.findByState(PolicyState.STATE1);
        System.out.println("Найдено " + policies.size() + " полисов в STATE1 для обработки");

        for (Policy policy : policies) {
            System.out.println("Обработка полиса: " + policy.getId());
            stateMachineService.processPolicyState1ToState2(policy);
        }
    }

    public void processAllState2ToState3() {
        List<Policy> policies = policyRepository.findByState(PolicyState.STATE2);
        System.out.println("Найдено " + policies.size() + " полисов в STATE2 для обработки");

        for (Policy policy : policies) {
            System.out.println("Обработка полиса: " + policy.getId());
            stateMachineService.processPolicyState2ToState3(policy);
        }
    }

    public List<Policy> getAllPolicies() {
        return policyRepository.findAll();
    }
}