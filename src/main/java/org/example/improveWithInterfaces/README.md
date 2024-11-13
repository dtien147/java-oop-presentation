# Improved Design with Interfaces and Factory Pattern

In this Approach, we improve the flexibility and maintainability of the system by introducing **interfaces** and a **factory pattern**. Here are the key improvements in code, with explanations.

## Key Improvements

1. **Interface-based Design**:
   - By introducing interfaces like `IAccount` and `IInterestBearingAccount`, we decouple specific account implementations from `AccountManager`.
   - This enables polymorphism, allowing `AccountManager` to interact with any `IAccount` implementation without needing to know the details of each account type.

2. **Account Factory**:
   - The `AccountFactory` class abstracts account creation. This eliminates the need for `AccountManager` to know about specific subclasses, reducing tight coupling.
   - Adding a new account type requires changes only in `AccountFactory`, not in `AccountManager`, adhering to the **Open/Closed Principle**.

3. **Separation of Interest-bearing Behavior**:
   - By creating an `IInterestBearingAccount` interface, interest-bearing functionality can be implemented independently.
   - This allows for flexibility in creating different types of accounts that can calculate interest without being locked into specific subclasses.

## Improved Project Code

### IAccount.java (Interface for Basic Account Operations)

```java
public interface IAccount {
    void deposit(double amount);
    void withdraw(double amount);
    List<Transaction> getTransactionHistory();
}
```

### Improvement
`IAccount` provides a common interface for all account types, allowing `AccountManager` to operate on any `IAccount` without knowing specific details.

### IInterestBearingAccount.java (Interface for Interest-bearing Accounts)
```java
public interface IInterestBearingAccount extends IAccount {
    void calculateInterest();
}
```

**Improvement:** This interface extends `IAccount` and adds `calculateInterest`, providing a flexible way to add interest-bearing functionality to any account type that implements this interface.

### BankAccount.java (Implements IAccount as a General Account)
```java
public class BankAccount implements IAccount {
    protected String accountNumber;
    protected double balance;
    protected List<Transaction> transactions = new ArrayList<>();

    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        transactions.add(new Transaction("deposit", amount, new Date(), "Deposit made"));
    }

    @Override
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactions.add(new Transaction("withdrawal", amount, new Date(), "Withdrawal made"));
        } else {
            System.out.println("Insufficient funds");
        }
    }

    @Override
    public List<Transaction> getTransactionHistory() {
        return transactions;
    }
}
```

**Improvement:** `BankAccount` implements `IAccount`, making it compatible with `AccountManager` while providing a basic structure that can be extended.

### SavingsAccount.java (Implements IInterestBearingAccount for Interest Calculation)
```java
public class SavingsAccount extends BankAccount implements IInterestBearingAccount {
    private double interestRate;

    public SavingsAccount(String accountNumber, double balance, double interestRate) {
        super(accountNumber, balance);
        this.interestRate = interestRate;
    }

    @Override
    public void calculateInterest() {
        double interest = balance * interestRate;
        deposit(interest);
        transactions.add(new Transaction("interest", interest, new Date(), "Interest credited"));
    }
}
```

**Improvement:** `SavingsAccount` implements `IInterestBearingAccount`, allowing it to calculate interest. Interest-bearing behavior is no longer tied to subclasses, providing flexibility.

### AccountFactory.java (Factory Pattern for Account Creation)
```java
public class AccountFactory {
    public IAccount createAccount(String type, String accountNumber, double initialBalance, double additionalInfo) {
        switch (type) {
            case "checking":
                return new CheckingAccount(accountNumber, initialBalance, additionalInfo);
            case "savings":
                return new SavingsAccount(accountNumber, initialBalance, additionalInfo);
            case "loan":
                return new LoanAccount(accountNumber, initialBalance, additionalInfo);
            default:
                return new BankAccount(accountNumber, initialBalance);
        }
    }
}
```

**Improvement:** `AccountFactory` centralizes account creation, making it easy to add or modify account types without impacting `AccountManager`.

### AccountManager.java (Using Interfaces and Factory Pattern)
```java
public class AccountManager {
    private List<Customer> customers = new ArrayList<>();
    private AccountFactory accountFactory = new AccountFactory();

    public IAccount createAccount(String type, String customerName, double initialBalance, double additionalInfo) {
        IAccount account = accountFactory.createAccount(type, "ACC" + System.currentTimeMillis(), initialBalance, additionalInfo);

        Customer customer = findCustomerByName(customerName);
        if (customer == null) {
            customer = new Customer(customerName);
            customers.add(customer);
        }
        customer.addAccount(account);
        return account;
    }

    public void transferFunds(IAccount accountFrom, IAccount accountTo, double amount) {
        accountFrom.withdraw(amount);
        accountTo.deposit(amount);
    }

    private Customer findCustomerByName(String name) {
        return customers.stream().filter(c -> c.name.equals(name)).findFirst().orElse(null);
    }
}
```

**Improvement:** `AccountManager` now uses `IAccount` for all account interactions, making it agnostic to specific implementations. It also relies on `AccountFactory` for account creation, adhering to the Open/Closed Principle by reducing dependencies on specific account classes.

---

### Summary of Improvements
#### Decoupling Through Interfaces:
Interfaces like `IAccount` and `IInterestBearingAccount` abstract account functionalities, allowing `AccountManager` to work with any account type.

**Improvement:** This increases flexibility and maintainability, as changes to specific account types don’t impact `AccountManager`.

#### Centralized Account Creation Using Factory Pattern:
`AccountFactory` centralizes account creation logic, making it easy to add or modify account types in one place.

**Improvement:** This reduces code duplication and simplifies `AccountManager`, which no longer needs to know about specific account implementations.

#### Enhanced Extensibility:
With interfaces, adding a new account type or a new behavior (like a new kind of interest calculation) can be done without modifying `AccountManager`.

**Improvement:** This design adheres to the Open/Closed Principle, allowing the system to be extended without modifying core functionality.

#### Improved Code Reusability:
By separating interest-bearing behavior into an interface, any account type (not just `SavingsAccount`) can implement `IInterestBearingAccount` to have interest functionality.

**Improvement:** This allows code reuse across account types and avoids locking specific functionality to inheritance-based subclasses.

These improvements make Approach 2 more flexible, extensible, and maintainable than Approach 1, as it adheres to better design principles and patterns.

