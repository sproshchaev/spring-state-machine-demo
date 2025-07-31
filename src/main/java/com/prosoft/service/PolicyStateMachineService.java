package com.prosoft.service;

import com.prosoft.Repository.PolicyRepository;
import com.prosoft.model.Policy;
import com.prosoft.model.PolicyEvents;
import com.prosoft.model.PolicyState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.stereotype.Service;

@Service
public class PolicyStateMachineService {

    @Autowired
    private StateMachineFactory<PolicyState, PolicyEvents> stateMachineFactory;

    @Autowired
    private PolicyRepository policyRepository;

    public void processPolicyState1ToState2(Policy policy) {
        StateMachine<PolicyState, PolicyEvents> stateMachine = stateMachineFactory.getStateMachine(policy.getId());

        try {
            // Устанавливаем контекст
            stateMachine.getExtendedState().getVariables().put("policy", policy);

            // Запускаем машину состояний
            stateMachine.start();

            // Отправляем событие
            stateMachine.sendEvent(PolicyEvents.PROCESS_TO_STATE2);

            // ВАЖНО: Сохраняем обновленное состояние из объекта policy
            // Так как в Action мы уже обновили состояние policy
            policyRepository.save(policy);
            System.out.println("Полис " + policy.getId() + " обработан, новое состояние: " + policy.getState());

        } finally {
            stateMachine.stop();
        }
    }

    public void processPolicyState2ToState3(Policy policy) {
        StateMachine<PolicyState, PolicyEvents> stateMachine = stateMachineFactory.getStateMachine(policy.getId());

        try {
            // Устанавливаем контекст
            stateMachine.getExtendedState().getVariables().put("policy", policy);

            // Запускаем машину состояний
            stateMachine.start();

            // Отправляем событие
            stateMachine.sendEvent(PolicyEvents.PROCESS_TO_STATE3);

            // ВАЖНО: Сохраняем обновленное состояние из объекта policy
            // Так как в Action мы уже обновили состояние policy
            policyRepository.save(policy);
            System.out.println("Полис " + policy.getId() + " обработан, новое состояние: " + policy.getState());

        } finally {
            stateMachine.stop();
        }
    }
}