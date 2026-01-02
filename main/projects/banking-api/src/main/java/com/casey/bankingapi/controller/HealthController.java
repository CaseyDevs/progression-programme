package com.casey.bankingapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
class HealthController {
    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}
