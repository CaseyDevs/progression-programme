# What are Transactions?

## Transaction flow in Spring
When a method annotated with @Transactional starts:
- Spring opens a database transaction.
- Spring creates a persistent context (EntityManager).
- Entities inside the method become managed.

### What is a database transaction?
A single, logical unit of work compromising one or more database operations (like CRUD) that must be treated as an
indivisible whole. Either all operations succeed and saved (commit), or none of them take effect (rollback).

### What is a manged entity?
A managed entity is tracked by JPA where it is compared to its original state via snapshots and automatically
synchronised with the database. 

### Dirty Checking
When a transactional method ends:
- JPA compares the current entity state with the snapshot.
- Detects the differences in state ("dirty fields").
- Generates the required "UPDATE" SQL.
- Commits the transaction.

### Read-only Transactions
To cut overhead (SQL Flushes), we can set read-only transactions in Spring for non-write methods.

`@Transactional(readOnly = true)`