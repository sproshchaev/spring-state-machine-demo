package com.prosoft.service;

import com.prosoft.model.Events;
import com.prosoft.model.States;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;

@Service
public class LightBulbService {

    // Внедряем сконфигурированный StateMachine
    @Autowired
    private StateMachine<States, Events> stateMachine;

    // Метод, вызываемый после завершения внедрения зависимостей
    @PostConstruct
    public void init() {
        // Запускаем StateMachine
        if (stateMachine.getState() == null) {
            stateMachine.start();
        }
    }

    // Метод для включения/выключения лампочки
    public void toggle() {
        // Отправляем событие TOGGLE в StateMachine
        stateMachine.sendEvent(MessageBuilder.withPayload(Events.TOGGLE).build());
    }

    // Метод для получения текущего состояния
    public States getCurrentState() {
        // Получаем текущее состояние из StateMachine
        return stateMachine.getState().getId();
    }

}
