package org.example.problem.accounts;

import org.example.problem.Transaction;

import java.util.Date;

public class LoanAccount extends BankAccount {
    private double interestRate;

    public LoanAccount(String accountNumber, double balance, double interestRate) {
        super(accountNumber, balance);
        this.interestRate = interestRate;
    }

    public void calculateInterest() {
        double interest = balance * interestRate;
        balance += interest;
        transactions.add(new Transaction("interest", interest, new Date(), "Interest accrued on loan"));
    }
}
