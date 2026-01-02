package com.casey.bankingapi.service;

import org.springframework.stereotype.Service;

@Service
public class BankingService {

    public String getWelcomeMessage(String name) {
        return "Welcome " + name + "!";
    }
}
