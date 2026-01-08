# What are specifications?
Specifications allow you to build dynamic, type-safe queries at runtime using the Criteria API.

"Specification controls which data you fetch"

## Purpose
- Used when filters are optional or combinable
- Avoids large `if/else` blocks and method explosion
- Filters can be composed (AND/OR) cleanly
- Ideal for search endpoints (e.g, filter by type, balance, date)

## Implementation
Spring provides the @RequestParam annotation for us to easily get the parameters passed in a HTTP request.
We have the choice to make search params required.

**Controller**:
```java
@GetMapping("/accounts")
    public ResponseEntity<Page<AccountResponseDto>> getAccounts(
            @RequestParam(required = false) String type,
            @RequestParam(required = false)BigDecimal minBalance,
            Pageable pageable
    ) {
        return ResponseEntity.ok(accountService.getAllAccounts(type, minBalance, pageable));
```

At the repository level, we create a specifications class that directly implements the Criteria API to check if 
particular parameters are passed.

**Repository Level**:
```java
public class AccountSpecifications {

    public static Specification<Account> hasType(String type) {
        // Returning null will ignore the filter
        return (root, query, cb) ->
                type == null ? null : cb.equal(root.get("accountType"), type);
    }

    public static Specification<Account> hasMinBalance(BigDecimal minBalance) {
        return (root, query, cb) ->
                minBalance == null ? null :
                        cb.greaterThanOrEqualTo(root.get("balance"), minBalance);
    }
}
```

We then update our service to implement the specification. We define the specification and then pass it to the database
method, where content is filtered automatically.

```java
@Transactional(readOnly = true)
    public Page<AccountResponseDto> getAllAccounts(String type, BigDecimal minBalance, Pageable pageable) {
        // Map accounts to dto
        Specification<Account> spec =
                Specification.where(hasType(type))
                        .and(hasMinBalance(minBalance));


        return accountRepo.findAll(spec, pageable)
                    .map(account -> new AccountResponseDto(
                            account.getAccountName(),
                            account.getAccountType(),
                            account.getBalance(),
                            new UserResponseDto(account.getUser().getName())
                    ));
    }
```