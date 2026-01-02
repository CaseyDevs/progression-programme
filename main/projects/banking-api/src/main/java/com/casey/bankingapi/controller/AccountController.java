package com.casey.bankingapi.controller;

import com.casey.bankingapi.dto.AccountResponseDto;
import com.casey.bankingapi.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/api")
class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts")
    public List<AccountResponseDto> getAccounts() {
        return accountService.getAllAccounts();
    }

    @PostMapping("/accounts")
    public void createAccount(@RequestBody AccountResponseDto request) {
        accountService.createAccount(
                request.accountName(),
                request.accountType(),
                request.balance()
        );
    }
}
