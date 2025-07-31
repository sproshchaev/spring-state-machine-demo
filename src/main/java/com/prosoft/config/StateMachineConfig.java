package com.prosoft.config;

import com.prosoft.model.Events;
import com.prosoft.model.States;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

@Configuration
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {

    // Настраиваем состояния
    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states) throws Exception {
        states
                .withStates()
                // Указываем начальное состояние
                .initial(States.OFF)
                // Определяем все возможные состояния
                .states(EnumSet.allOf(States.class));
    }

    // Настраиваем переходы
    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {
        transitions
                // Из состояния OFF по событию TOGGLE переходим в ON
                .withExternal()
                .source(States.OFF).target(States.ON)
                .event(Events.TOGGLE)
                .and()
                // Из состояния ON по событию TOGGLE переходим в OFF
                .withExternal()
                .source(States.ON).target(States.OFF)
                .event(Events.TOGGLE);
    }


}
