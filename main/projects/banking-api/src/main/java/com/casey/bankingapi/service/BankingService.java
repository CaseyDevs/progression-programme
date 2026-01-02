package com.casey.bankingapi.service;

import com.casey.bankingapi.dto.AccountDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankingService {

    public String getWelcomeMessage(String name) {
        return "Welcome " + name + "!";
    }
}
