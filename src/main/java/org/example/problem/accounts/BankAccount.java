package org.example.problem.accounts;

import org.example.problem.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BankAccount {
    // Mistake: Public fields violate encapsulation, allowing direct modification of account state.
    // Solution: Make fields private and provide getters/setters to enforce control over data.
    public String accountNumber;
    public double balance;
    public List<Transaction> transactions = new ArrayList<>();

    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add(new Transaction("deposit", amount, new Date(), "Deposit made"));
    }

    public void withdraw(double amount) {
        // Mistake: Withdraw logic doesn't handle overdraft or account-specific rules.
        // Solution: Consider using composition or interfaces to add custom withdrawal behavior.
        if (balance >= amount) {
            balance -= amount;
            transactions.add(new Transaction("withdrawal", amount, new Date(), "Withdrawal made"));
        } else {
            System.out.println("Insufficient funds");
        }
    }

    public List<Transaction> getTransactionHistory() {
        return transactions;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
