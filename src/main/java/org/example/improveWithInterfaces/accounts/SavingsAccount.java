package org.example.improveWithInterfaces.accounts;

import org.example.improveWithInterfaces.Transaction;
import org.example.improveWithInterfaces.interfaces.IInterestBearingAccount;

import java.util.Date;

public class SavingsAccount extends BankAccount implements IInterestBearingAccount {
    private double interestRate;

    public SavingsAccount(String accountNumber, double balance, double interestRate) {
        super(accountNumber, balance);
        this.interestRate = interestRate;
    }

    @Override
    public void calculateInterest() {
        double interest = balance * interestRate;
        deposit(interest);
        transactions.add(new Transaction("interest", interest, new Date(), "Interest credited"));
    }
}
