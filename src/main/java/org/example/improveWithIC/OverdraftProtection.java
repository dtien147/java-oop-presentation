package org.example.improveWithIC;

import org.example.improveWithIC.interfaces.IOverdraft;

public class OverdraftProtection implements IOverdraft {
    private double overdraftLimit;

    public OverdraftProtection(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public boolean canWithdraw(double amount, double balance) {
        return balance + overdraftLimit >= amount;
    }
}
