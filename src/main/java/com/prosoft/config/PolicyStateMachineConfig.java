package com.prosoft.config;

import com.prosoft.model.Policy;
import com.prosoft.model.PolicyEvents;
import com.prosoft.model.PolicyState;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;

import java.util.Random;

@Configuration
@EnableStateMachineFactory
public class PolicyStateMachineConfig extends StateMachineConfigurerAdapter<PolicyState, PolicyEvents> {

    @Override
    public void configure(StateMachineStateConfigurer<PolicyState, PolicyEvents> states) throws Exception {
        states.withStates()
                .initial(PolicyState.STATE1)
                .state(PolicyState.STATE1)
                .state(PolicyState.STATE2)
                .end(PolicyState.STATE3);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<PolicyState, PolicyEvents> transitions) throws Exception {
        transitions
                .withExternal()
                .source(PolicyState.STATE1)
                .target(PolicyState.STATE2)
                .event(PolicyEvents.PROCESS_TO_STATE2)
                .guard(state1Guard())
                .action(processState1Action())
                .and()
                .withExternal()
                .source(PolicyState.STATE2)
                .target(PolicyState.STATE3)
                .event(PolicyEvents.PROCESS_TO_STATE3)
                .guard(state2Guard())
                .action(processState2Action());
    }

    // Guards
    @Bean
    public Guard<PolicyState, PolicyEvents> state1Guard() {
        return context -> {
            Policy policy = context.getExtendedState().get("policy", Policy.class);
            if (policy == null) return false;
            // Проверяем, что полис действительно в STATE1
            return policy.getState() == PolicyState.STATE1 && policy.getRetryCount() < 5;
        };
    }

    @Bean
    public Guard<PolicyState, PolicyEvents> state2Guard() {
        return context -> {
            Policy policy = context.getExtendedState().get("policy", Policy.class);
            if (policy == null) return false;
            // Проверяем, что полис действительно в STATE2
            return policy.getState() == PolicyState.STATE2 && policy.getRetryCount() < 5;
        };
    }

    // Actions - ИСПРАВЛЕНЫ (теперь правильно обновляют состояние в объекте policy)
    @Bean
    public Action<PolicyState, PolicyEvents> processState1Action() {
        return context -> {
            Policy policy = context.getExtendedState().get("policy", Policy.class);
            if (policy != null) {
                System.out.println("Обработка STATE1 → STATE2 для полиса: " + policy.getId());
                // Эмуляция обработки с вероятностью успеха 70%
                boolean success = new Random().nextDouble() < 0.7;
                if (success) {
                    System.out.println("✅ Успешная обработка STATE1 для полиса: " + policy.getId());
                    policy.setLastError("");
                    policy.setState(PolicyState.STATE2); // ВАЖНО: Обновляем состояние в объекте
                } else {
                    System.out.println("❌ Неудачная обработка STATE1 для полиса: " + policy.getId());
                    policy.setRetryCount(policy.getRetryCount() + 1);
                    policy.setLastError("Ошибка обработки STATE1, попытка " + policy.getRetryCount());
                    // Остаемся в STATE1
                }
            }
        };
    }

    @Bean
    public Action<PolicyState, PolicyEvents> processState2Action() {
        return context -> {
            Policy policy = context.getExtendedState().get("policy", Policy.class);
            if (policy != null) {
                System.out.println("Обработка STATE2 → STATE3 для полиса: " + policy.getId());
                // Эмуляция обработки с вероятностью успеха 70%
                boolean success = true; //new Random().nextDouble() < 0.7;
                if (success) {
                    System.out.println("✅ Успешная обработка STATE2 для полиса: " + policy.getId());
                    policy.setLastError("");
                    policy.setState(PolicyState.STATE3); // ВАЖНО: Обновляем состояние в объекте
                } else {
                    System.out.println("❌ Неудачная обработка STATE2 для полиса: " + policy.getId());
                    policy.setRetryCount(policy.getRetryCount() + 1);
                    policy.setLastError("Ошибка обработки STATE2, попытка " + policy.getRetryCount());
                    // Остаемся в STATE2
                }
            }
        };
    }
}