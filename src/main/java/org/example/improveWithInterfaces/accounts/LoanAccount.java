package org.example.improveWithInterfaces.accounts;

import org.example.improveWithInterfaces.Transaction;
import org.example.improveWithInterfaces.interfaces.IInterestBearingAccount;

import java.util.Date;

public class LoanAccount extends BankAccount implements IInterestBearingAccount {
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
