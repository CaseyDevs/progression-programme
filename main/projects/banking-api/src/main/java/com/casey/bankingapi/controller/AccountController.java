package com.casey.bankingapi.controller;

import com.casey.bankingapi.dto.AccountResponseDto;
import com.casey.bankingapi.dto.CreateAccountRequestDto;
import com.casey.bankingapi.dto.UpdateAccountFieldRequestDto;
import com.casey.bankingapi.dto.UpdateAccountRequestDto;
import com.casey.bankingapi.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts")
    public ResponseEntity<Page<AccountResponseDto>> getAccounts(
            @RequestParam(required = false) String type,
            @RequestParam(required = false)BigDecimal minBalance,
            Pageable pageable
    ) {
        return ResponseEntity.ok(accountService.getAllAccounts(type, minBalance, pageable));
    }

    @PostMapping("/accounts")
    public ResponseEntity<Void> createAccount(
            @Valid @RequestBody CreateAccountRequestDto request
    ) {
        accountService.createAccount(
                request.userName(),
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

    @PatchMapping("/accounts/{name}")
    public ResponseEntity<AccountResponseDto> updateAccountField(
            @PathVariable String name,
            @Valid @RequestBody UpdateAccountFieldRequestDto request
            ) {

        AccountResponseDto updated = accountService.updateAccountField(name, request);

        return ResponseEntity.ok(updated);
    }
}
