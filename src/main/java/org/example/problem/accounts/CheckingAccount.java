package org.example.problem.accounts;

import org.example.problem.Transaction;

import java.util.Date;

public class CheckingAccount extends BankAccount {
    public double overdraftLimit;

    public CheckingAccount(String accountNumber, double balance, double overdraftLimit) {
        super(accountNumber, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) {
        // Mistake: Overloading withdraw logic within a subclass, which may lead to code duplication if other accounts need custom logic.
        // Solution: Use composition to add overdraft behavior, making it more reusable and configurable.
        if (balance + overdraftLimit >= amount) {
            balance -= amount;
            transactions.add(new Transaction("withdrawal", amount, new Date(), "Withdrawal with overdraft"));
        } else {
            System.out.println("Overdraft limit exceeded");
        }
    }
}
