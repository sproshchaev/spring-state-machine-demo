package com.prosoft.controller;

import com.prosoft.service.LightBulbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lightbulb")
public class LightBulbController {

    @Autowired
    private LightBulbService lightBulbService;

    @GetMapping("/toggle")
    public String toggle() {
        lightBulbService.toggle();
        return "Current state: " + lightBulbService.getCurrentState();
    }

    @GetMapping("/status")
    public String getStatus() {
        return "Current state: " + lightBulbService.getCurrentState();
    }
}