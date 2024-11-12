package org.example.problem.accounts;

import org.example.problem.Transaction;

import java.util.Date;

public class SavingsAccount extends BankAccount {
    public double interestRate;

    public SavingsAccount(String accountNumber, double balance, double interestRate) {
        super(accountNumber, balance);
        this.interestRate = interestRate;
    }

    public void calculateInterest() {
        // Mistake: Interest calculation is tightly coupled with the SavingsAccount class.
        // Solution: Use an interface or composition to make interest-bearing behavior reusable across different account types.
        double interest = balance * interestRate;
        deposit(interest);
        transactions.add(new Transaction("interest", interest, new Date(), "Interest credited"));
    }
}
