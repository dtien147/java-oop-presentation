package org.example.improveWithIC;

import org.example.improveWithIC.interfaces.IAccount;
import org.example.improveWithIC.interfaces.IInterest;
import org.example.improveWithIC.interfaces.IOverdraft;

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

    public String getAccountNumber() {
        return accountNumber;
    }
}
