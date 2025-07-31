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
    private StateMachine<PolicyState, PolicyEvents> stateMachine;

    public void processState1ToState2() {
        List<Policy> policies = policyRepository.findByState(PolicyState.STATE1);
        for (Policy policy : policies) {
            if (simulateProcessing()) {
                stateMachine.getState();
                policy.setState(PolicyState.STATE2);
                policyRepository.save(policy);
            }
        }
    }

    public void processState2ToState3() {
        List<Policy> policies = policyRepository.findByState(PolicyState.STATE2);
        for (Policy policy : policies) {
            if (simulateProcessing()) {
                policy.setState(PolicyState.STATE3);
                policyRepository.save(policy);
            }
        }
    }

    private boolean simulateProcessing() {
        // 70% успеха
        return new Random().nextDouble() < 0.7;
    }

    public List<Policy> getAllPolicies() {
        return policyRepository.findAll();
    }

    public Policy createPolicy() {
        String id = "POLICY_" + System.currentTimeMillis();
        Policy policy = new Policy(id);
        policyRepository.save(policy);
        return policy;
    }
}