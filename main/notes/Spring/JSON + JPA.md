# Why bidirectional relationships are dangerous
When working with JSON in Spring, it is important to recognise that Spring uses Jackson--library for JSON serialisation
in Java. Jackson is used to inspect objects, covert them to JSON, walk though each field, recursively serialise
references.

The issue with Jackson when working with JPA is infinite recursion as Jackson sees the following:
```sql
Account
 ├─ accountName
 ├─ balance
 └─ user
       ├─ name
       └─ accounts
             ├─ account
                   └─ user
                         └─ accounts
                               ...
```

This behaviour is expected as JPA and Jackson are completely independent systems.
To avoid this behaviour, we use DTO's and never return JPA entities directly from a controller.

DTOs prevent infinite recursion, avoid lazy-loading issues, decouple persistence models from API contracts, and give us full control over serialized output.