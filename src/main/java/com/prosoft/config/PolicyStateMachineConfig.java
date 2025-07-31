package com.prosoft.config;

import com.prosoft.model.PolicyEvents;
import com.prosoft.model.PolicyState;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

@Configuration
@EnableStateMachine
public class PolicyStateMachineConfig extends StateMachineConfigurerAdapter<PolicyState, PolicyEvents> {

    @Override
    public void configure(StateMachineStateConfigurer<PolicyState, PolicyEvents> states) throws Exception {
        states.withStates()
                .initial(PolicyState.STATE1)
                .states(EnumSet.allOf(PolicyState.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<PolicyState, PolicyEvents> transitions) throws Exception {
        transitions
                .withExternal()
                .source(PolicyState.STATE1).target(PolicyState.STATE2)
                .event(PolicyEvents.PROCESS_TO_STATE2)
                .and()
                .withExternal()
                .source(PolicyState.STATE2).target(PolicyState.STATE3)
                .event(PolicyEvents.PROCESS_TO_STATE3);
    }
}