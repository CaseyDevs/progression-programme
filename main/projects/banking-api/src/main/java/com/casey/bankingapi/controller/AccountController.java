package com.casey.bankingapi.controller;

import com.casey.bankingapi.dto.AccountResponseDto;
import com.casey.bankingapi.dto.CreateAccountRequest;
import com.casey.bankingapi.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<AccountResponseDto>> getAccounts() {
        List<AccountResponseDto> accounts = accountService.getAllAccounts();


        return ResponseEntity.ok(accounts);
    }

    @PostMapping("/accounts")
    public ResponseEntity<Void> createAccount(@Valid @RequestBody CreateAccountRequest request) {
        accountService.createAccount(
                request.accountName(),
                request.accountType(),
                request.balance()
        );

        return ResponseEntity.ok().build();
    }

    @GetMapping("/accounts/{name}")
    public ResponseEntity<AccountResponseDto> getAccountByName(@PathVariable String name) {
        AccountResponseDto account = accountService.getAccountByName(name);

        return ResponseEntity.ok(account);
    }
}
