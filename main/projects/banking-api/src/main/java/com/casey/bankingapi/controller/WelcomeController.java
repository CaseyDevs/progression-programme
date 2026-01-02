package com.casey.bankingapi.controller;

import com.casey.bankingapi.service.BankingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
class WelcomeController {

    private final BankingService bankingService;

    public WelcomeController(BankingService bankingService) {
        this.bankingService = bankingService;
    }

    @GetMapping("/welcome")
    public String welcome(@RequestParam String name) {
        return bankingService.getWelcomeMessage(name);
    }

}
