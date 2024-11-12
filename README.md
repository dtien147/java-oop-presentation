# Banking System - Approach 1: Inheritance-Based Design

## Project Structure

In **Approach 1**, the system is structured using inheritance, with a base `BankAccount` class and specific account types inheriting from it. Here’s a breakdown of each class and its role:

1. **BankAccount**:
    - The base class representing a generic bank account.
    - Contains basic account operations like `deposit`, `withdraw`, and `getTransactionHistory`.
    - Fields include `accountNumber`, `balance`, and `transactions` (a list of transaction history).

2. **CheckingAccount**:
    - Inherits from `BankAccount`.
    - Adds an overdraft feature with an `overdraftLimit` field.
    - Overrides `withdraw` to allow withdrawals up to the overdraft limit.

3. **SavingsAccount**:
    - Inherits from `BankAccount`.
    - Adds an interest calculation feature with an `interestRate` field.
    - Includes a `calculateInterest` method to add interest to the account balance.

4. **LoanAccount**:
    - Inherits from `BankAccount`.
    - Represents a loan account with an `interestRate` field.
    - Includes an `accrueInterest` method to increase the loan balance by an interest amount.

5. **AccountManager**:
    - Manages account creation and transactions.
    - Contains a list of `Customer` objects, each with a list of `BankAccount` instances.
    - Has methods to create accounts, transfer funds between accounts, and retrieve customer information.

6. **Customer**:
    - Represents a bank customer.
    - Contains a `name` field and a list of `BankAccount` objects.
    - Provides an `addAccount` method to associate accounts with the customer.

7. **Transaction**:
    - Represents a single transaction with details about the transaction type, amount, date, and description.
    - Each `BankAccount` instance stores a list of `Transaction` instances as its transaction history.

## Use Cases

The use cases below demonstrate the functionality supported by the inheritance-based design in Approach 1.

### 1. Create an Account

**Description**: Create a new account of a specific type for a customer (e.g., checking, savings, or loan).
- **Input**: Account type (`checking`, `savings`, `loan`), customer name, initial balance, and additional parameters (like overdraft limit or interest rate).
- **Output**: A new account is created and associated with the specified customer.

**Example**:
   ```java
   BankAccount checkingAccount = accountManager.createAccount("checking", "Alice", 500, 200);
   BankAccount savingsAccount = accountManager.createAccount("savings", "Alice", 1000, 0.03);
   ```
### 2. Deposit Funds

**Description**: Deposit an amount of money into an existing account.
   - **Input**: Account instance and deposit amount.
   - **Output**: The balance of the account increases by the deposit amount, and a deposit transaction is added to the account’s transaction history.

   **Example**:
   ```java
   checkingAccount.deposit(200);
   ```

### 3. Withdraw Funds

**Description**: Withdraw an amount of money from an account, subject to available funds or overdraft limit.
   - **Input**: Account instance and withdrawal amount.
   - **Output**: The balance decreases by the withdrawal amount if there are sufficient funds (or if within the overdraft limit). A withdrawal transaction is recorded in the transaction history.

   **Example**:
   ```java
   checkingAccount.withdraw(300);
   ```

### 4. Calculate Interest on Savings Account

**Description**: Calculate and add interest to a savings account based on its balance and interest rate.
   - **Input**: `SavingsAccount` instance.
   - **Output**: The balance increases by the calculated interest amount, and an interest transaction is recorded in the account’s transaction history.

   **Example**:
   ```java
   if (savingsAccount instanceof SavingsAccount) {
       ((SavingsAccount) savingsAccount).calculateInterest();
   }
   ```
   
### 5. Accrue Interest on Loan Account

**Description**: Add accrued interest to a loan account, increasing the outstanding balance.
   - **Input**: `LoanAccount` instance.
   - **Output**: The loan balance increases by the accrued interest, and an interest transaction is recorded in the transaction history.

   **Example**:
   ```java
   if (loanAccount instanceof LoanAccount) {
       ((LoanAccount) loanAccount).accrueInterest();
   }
   ```
   
### 6. Transfer Funds Between Accounts

**Description**: Transfer funds from one account to another.
   - **Input**: Source account, target account, and transfer amount.
   - **Output**: Funds are withdrawn from the source account and deposited into the target account. Both accounts record the transfer in their transaction histories.

   **Example**:
   ```java
   accountManager.transferFunds(checkingAccount, savingsAccount, 100);
   ```
### 7. View Transaction History

**Description**: Display the transaction history of an account, including deposits, withdrawals, interest accruals, and transfers.
   - **Input**: Account instance.
   - **Output**: A list of transactions associated with the account, showing the transaction type, amount, date, and description.

   **Example**:
   ```java
   for (Transaction transaction : checkingAccount.getTransactionHistory()) {
       System.out.println(transaction);
   }
   ```