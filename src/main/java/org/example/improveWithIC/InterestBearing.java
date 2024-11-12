package org.example.improveWithIC;

import org.example.improveWithIC.interfaces.IInterest;

public class InterestBearing implements IInterest {
    private double interestRate;

    public InterestBearing(double interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public double calculateInterest(double balance) {
        return balance * interestRate;
    }
}
