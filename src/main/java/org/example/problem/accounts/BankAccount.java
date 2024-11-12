package org.example.problem.accounts;

import org.example.problem.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BankAccount {
    protected String accountNumber;
    protected double balance;
    protected List<Transaction> transactions = new ArrayList<>();

    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add(new Transaction("deposit", amount, new Date(), "Deposit made"));
    }

    public void withdraw(double amount) {
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
