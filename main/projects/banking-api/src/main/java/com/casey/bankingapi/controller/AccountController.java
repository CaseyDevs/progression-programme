package com.casey.bankingapi.controller;

import com.casey.bankingapi.dto.AccountDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.casey.bankingapi.service.BankingService;

import java.util.List;

@RestController
@RequestMapping("/api")
class AccountController {

    private final BankingService bankingService;

    public AccountController(BankingService bankingService) {
        this.bankingService = bankingService;
    }

    @GetMapping("/accounts")
    public List<AccountDto> getAccounts() {
        return bankingService.getAllAccounts();
    }

    @PostMapping("/accounts")
    public void createAccount(@RequestBody AccountDto request) {
        bankingService.createAccount(request.name());
    }
}
