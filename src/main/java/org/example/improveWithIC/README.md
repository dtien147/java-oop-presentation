### Approach: Using Composition
In Approach 3, we move away from inheritance and instead use composition to add account behaviors dynamically. This approach allows us to add or remove behaviors (such as overdraft protection or interest-bearing functionality) as needed, making the system more flexible, maintainable, and scalable.

### Key Improvements
#### Composition over Inheritance:
- Instead of a rigid inheritance hierarchy, we use composition to add behaviors to accounts. This approach allows us to dynamically mix and match functionalities, like overdraft protection and interest calculation, without creating specialized subclasses.
- This improvement adheres to the Single Responsibility Principle and Open/Closed Principle by allowing behaviors to be added or removed without modifying the base account class.

#### Modular Behavior Components:
- By creating modular classes like `OverdraftProtection` and `InterestBearing`, we can compose them into different account types as needed. This enhances code reuse and reduces duplication, as behaviors are no longer hardcoded into subclasses.
- This design makes the code more flexible and aligns with the Dependency Inversion Principle.

#### Decoupled Account Creation with Composition:
- The `AccountFactory` now creates `BankAccount` instances with specific behaviors added dynamically through composition, making it easy to add new features without modifying existing code.

### Improved Project Code
#### IAccount.java (Interface for Basic Account Operations)
```java
public interface IAccount {
    void deposit(double amount);
    void withdraw(double amount);
    List<Transaction> getTransactionHistory();
}
```

**Improvement:** The base `IAccount` interface is unchanged, ensuring that core account operations are accessible to all account types.

#### IOverdraft.java (Interface for Overdraft Protection Behavior)
```java
public interface IOverdraft {
    boolean canWithdraw(double amount, double balance);
}
```

**Improvement:** `IOverdraft` defines the overdraft protection behavior separately, allowing any account type to include or exclude overdraft functionality dynamically.

#### OverdraftProtection.java (Implements Overdraft Logic)
```java
public class OverdraftProtection implements IOverdraft {
    private double overdraftLimit;

    public OverdraftProtection(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public boolean canWithdraw(double amount, double balance) {
        return balance + overdraftLimit >= amount;
    }
}
```

**Improvement:** This class encapsulates overdraft logic, making it reusable across different account types.

#### IInterest.java (Interface for Interest-bearing Behavior)
```java
public interface IInterest {
    double calculateInterest(double balance);
}
```

**Improvement:** The `IInterest` interface allows any account to calculate interest, decoupling the interest-bearing functionality from specific subclasses.

#### InterestBearing.java (Implements Interest Calculation)
```java
public class InterestBearing implements IInterest {
    private double interestRate;

    public InterestBearing(double interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public double calculateInterest(double balance) {
        return balance * interestRate;
    }
}
```

**Improvement:** `InterestBearing` encapsulates interest calculation logic, making it reusable and attachable to any account type.

#### BankAccount.java (Uses Composition for Flexible Behaviors)
```java
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BankAccount implements IAccount {
private String accountNumber;
private double balance;
private List<Transaction> transactions = new ArrayList<>();
private IOverdraft overdraft;
private IInterest interestCalculator;

    public BankAccount(String accountNumber, double balance, IOverdraft overdraft, IInterest interestCalculator) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.overdraft = overdraft;
        this.interestCalculator = interestCalculator;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        transactions.add(new Transaction("deposit", amount, new Date(), "Deposit made"));
    }

    @Override
    public void withdraw(double amount) {
        if (overdraft != null && overdraft.canWithdraw(amount, balance)) {
            balance -= amount;
            transactions.add(new Transaction("withdrawal", amount, new Date(), "Withdrawal with overdraft"));
        } else if (balance >= amount) {
            balance -= amount;
            transactions.add(new Transaction("withdrawal", amount, new Date(), "Withdrawal made"));
        } else {
            System.out.println("Insufficient funds");
        }
    }

    public void calculateInterest() {
        if (interestCalculator != null) {
            double interest = interestCalculator.calculateInterest(balance);
            deposit(interest);
            transactions.add(new Transaction("interest", interest, new Date(), "Interest credited"));
        }
    }

    @Override
    public List<Transaction> getTransactionHistory() {
        return transactions;
    }
}
```

**Improvement:** `BankAccount` now accepts `IOverdraft` and `IInterest` components, allowing it to include or exclude behaviors like overdraft protection and interest-bearing functionality as needed.

#### AccountFactory.java (Configures Accounts with Behaviors Using Composition)
```java
public class AccountFactory {
    public IAccount createAccount(String type, String accountNumber, double initialBalance, double additionalInfo) {
        switch (type) {
            case "checking":
                return new BankAccount(accountNumber, initialBalance, new OverdraftProtection(additionalInfo), null);
            case "savings":
                return new BankAccount(accountNumber, initialBalance, null, new InterestBearing(additionalInfo));
            case "loan":
                return new BankAccount(accountNumber, initialBalance, null, new InterestBearing(additionalInfo));
            default:
                return new BankAccount(accountNumber, initialBalance, null, null);
        }
    }
}
```

**Improvement:** `AccountFactory` now configures accounts with specific behaviors using composition, allowing for flexible addition of functionalities without modifying `BankAccount`.

This approach fully utilizes composition, providing a flexible, scalable, and maintainable design that adheres to SOLID principles. Each behavior is encapsulated, making it easy to add or modify functionalities independently.
