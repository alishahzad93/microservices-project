package com.aliments.microservices.limitsservice.controller;

import com.aliments.microservices.limitsservice.beans.Limits;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {
    @Value("${limits-service.minimum}")
    private Integer minimum;
    @Value("${limits-service.maximum}")
    private Integer maximum;
    @GetMapping("/limits")
    public Limits retriveLimits() {
        return new Limits(minimum,maximum);
    }
}
