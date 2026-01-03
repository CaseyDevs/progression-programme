package com.casey.bankingapi.controller;

import com.casey.bankingapi.dto.AccountResponseDto;
import com.casey.bankingapi.dto.CreateAccountRequestDto;
import com.casey.bankingapi.dto.UpdateAccountRequestDto;
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
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @PostMapping("/accounts")
    public ResponseEntity<Void> createAccount(
            @Valid @RequestBody CreateAccountRequestDto request
    ) {
        accountService.createAccount(
                request.accountName(),
                request.accountType(),
                request.balance()
        );

        return ResponseEntity.ok().build();
    }

    @GetMapping("/accounts/{name}")
    public ResponseEntity<AccountResponseDto> getAccountByName(@PathVariable String name) {
        return ResponseEntity.ok(accountService.getAccountByName(name));
    }

    @PutMapping("/accounts/{name}")
    public ResponseEntity<AccountResponseDto> updateAccount(
            @PathVariable String name,
            @Valid @RequestBody UpdateAccountRequestDto request
    ) {

        AccountResponseDto updated = accountService.updateAccount(name, request);
        return ResponseEntity.ok(updated);
    }
}
