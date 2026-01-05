# What is Java Persistence API?
JPA is a Java specification for managing relational data. It acts as an abstration layer that is used to simplify
database interactions by mapping Java objects to database tables using an ORM (typically hibernate).

## The JPA mental moel.
JPA Relationships are:
- Owned by one side (the side holding the foreign key)
- Explicitly declared in code.

***For User -> Account:***
- Each `account` belongs to one `User`.
- Each `User` can have many accounts.
- The foreign key e.g. `user_id` lives in `Account`.

```java
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(
        mappedBy = "user",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<Account> accounts = new ArrayList<>();

    protected User() {}

    public User(String name) {
        this.name = name;
    }
    
    // .... 
```

##