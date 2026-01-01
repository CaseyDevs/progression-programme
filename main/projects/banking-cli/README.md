
# BankingCLI

This project was structured to help me learn Java from scratch and gather a strong foundation of OOP experience.

## What it does
BankingCLI is a simple command-line banking application that allows users to create numerous accounts
(current or savings) and perform common banking operations on them- Deposit, Withdraw, Set goals, Apply interest, etc.

## Architecture Overview

The application follows a layered architecture with clear separation of concerns:

### Core Components
- **`bank/`** - Core banking domain models
    - `BankAccount` - Abstract base class for all account types
    - `CurrentAccount` - Standard checking account implementation
    - `SavingsAccount` - Savings account with interest and goal capabilities
    - `User` - Represents a banking customer
    - `Goal` - Represents savings goals
    - `BankingService` - Business logic and orchestration layer

- **`dto/`** - Data Transfer Objects
    - `AccountStatementDTO` - Immutable data structure for account statements
    - `GoalDTO` - Immutable data structure for goals

- **`helpers/`** - Utility classes
    - `InputHelpers` - User input validation and processing

- **`generators/`** - Statement generation (Strategy pattern)
    - `StatementGenerator` - Interface for different output formats
    - `TextStatementGenerator` - Plain text format
    - `JSONStatementGenerator` - JSON format
    - `CSVStatementGenerator` - CSV format

### Key Design Patterns Used
- **Inheritance**: `BankAccount` → `CurrentAccount`/`SavingsAccount`
- **Interface Segregation**: `SavingsCapable` interface for savings-specific operations
- **Strategy Pattern**: Multiple statement generators implementing `StatementGenerator`
- **Data Transfer Object**: Immutable DTOs for data transport
- **Exception Handling**: Custom `InvalidUserInputException` for business rule violations

### Features
-  Multiple account types (Current/Savings)
-  Basic banking operations (deposit, withdraw, balance check)
-  Transaction history tracking
-  Savings goals with progress tracking
-  Interest rate application for savings accounts
-  Multiple statement export formats (Text, JSON, CSV)
-  Account switching and management
-  Input validation and error handling

## How to Run
1. Navigate to the project directory
2. Compile: `javac -d out src/main/java/**/*.java`
3. Run: `java -cp out Main`
