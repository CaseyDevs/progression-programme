# What is the strategy pattern ?
The strategy pattern is a design pattern in that leverages interfaces to enable multiple classes to perform the
same job in different ways. For example, we could have a 'Statement Generator' - A general term that relates to 
bank statement generation. Now we could generate these statements in numerous ways, such as text, json, csv etc...
Logic for these different generations is different, so we use the strategy pattern to share these common methods 
between classes, but they all appraoch the job differently.

#### What varies ?
- The algorithm / behavior
  Different ways of doing the same job (e.g. different pricing rules, sorting algorithms, payment methods).

#### What stays the same ?
- The context and the contract
  A stable interface (e.g. PricingStrategy) and the code that uses it (the context) do not change.

## Why strategy pattens over conditionals ?
- Open/Closed Principle: add a new strategy by adding a new class — no need to edit existing logic.
- No growing conditionals: avoids large if/else or switch blocks that become brittle.
- Testability: each strategy can be unit-tested in isolation.
- Clear responsibility: behavior lives in its own class, not tangled inside the context.

### Strategy > Conditional Example:
```java
// Bad
if (type == A) { ... }
else if (type == B) { ... }
else if (type == C) { ... }
```

```java
// Good
strategy.execute();
```

### Strategy pattern in Spring
Spring makes Strategy idiomatic via dependency injection.

#### Interface
```java
public interface PricingStrategy {
    BigDecimal calculate(Order order);
}
```

#### Concrete Strategies
```java
@Service("standardPricing")
public class StandardPricing implements PricingStrategy { ... }

@Service("discountPricing")
public class DiscountPricing implements PricingStrategy { ... }
```

#### Context selects a strategy
```java
@Service
public class PricingService {

    private final PricingStrategy strategy;

    public PricingService(
        @Qualifier("discountPricing") PricingStrategy strategy
    ) {
        this.strategy = strategy;
    }

    public BigDecimal price(Order order) {
        return strategy.calculate(order);
    }
}
```

### What Spring gives you
- @Service → registers each strategy as a bean
- @Qualifier → selects which strategy to inject
- No if/else, no manual wiring