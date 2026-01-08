# What is Pagination?
Pagination is the process of getting data in small fixed-size chunks (pages) instead of loading everything at once.

"Pagination controls how much data you fetch"

## Implementation
We can implement pagination using `Page<T>` and `Pageable` in Spring.

**Controller**:
```java
@GetMapping("/accounts")
public ResponseEntity<Page<AccountResponseDto>> getAccounts(
        Pageable page;
        ) {
    return ResponseEntity.ok(accountService.getAllAccounts(pageable));
}
```

**Service**:
```java
public Page<AccountResponseDto> getAllAccounts(String type, BigDecimal minBalance, Pageable pageable) {
    return accountRepo.findAll(spec, pageable)
            .map(account -> new AccountResponseDto(
                    account.getAccountName(),
                    account.getAccountType(),
                    account.getBalance(),
                    new UserResponseDto(account.getUser().getName())
            ));
    }
```

This example will return metadata and a chuck of Accounts from the repository, rather than every account. The majority 
of pagination logic is handled by Spring.

## Benefits of Pagination
- Improves performance, memory usage, and API scalability
- Supports page number, page size, and sorting out of the box
- Common in REST API's to handle large datasets early
