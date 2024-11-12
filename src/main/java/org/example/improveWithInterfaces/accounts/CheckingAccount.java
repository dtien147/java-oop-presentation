package org.example.improveWithInterfaces.accounts;

import org.example.improveWithInterfaces.Transaction;

import java.util.Date;

public class CheckingAccount extends BankAccount {
    private double overdraftLimit;

    public CheckingAccount(String accountNumber, double balance, double overdraftLimit) {
        super(accountNumber, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) {
        if (balance + overdraftLimit >= amount) {
            balance -= amount;
            transactions.add(new Transaction("withdrawal", amount, new Date(), "Withdrawal with overdraft"));
        } else {
            System.out.println("Overdraft limit exceeded");
        }
    }
}
