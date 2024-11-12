package org.example.problem.accounts;

import org.example.problem.Transaction;

import java.util.Date;

public class LoanAccount extends BankAccount {
    public double interestRate;

    public LoanAccount(String accountNumber, double balance, double interestRate) {
        super(accountNumber, balance);
        this.interestRate = interestRate;
    }

    public void calculateInterest() {
        // Mistake: Interest logic is duplicated across account types, reducing code reusability.
        // Solution: Extract interest-bearing functionality into a separate class or interface for reuse.
        double interest = balance * interestRate;
        deposit(interest);
        transactions.add(new Transaction("interest", interest, new Date(), "Interest accrued on loan"));
    }
}
