package org.example.improveWithIC;

import org.example.improveWithIC.interfaces.IAccount;
import org.example.improveWithIC.interfaces.IAccountFactory;

public class AccountFactory implements IAccountFactory {
    @Override
    public IAccount createAccount(String type, String accountNumber, double initialBalance, double additionalInfo) {
        switch (type) {
            case "checking":
                return new BankAccount(accountNumber, initialBalance, new OverdraftProtection(additionalInfo), null);
            case "savings":
                return new BankAccount(accountNumber, initialBalance, null, new InterestBearing(additionalInfo));
            case "loan":
                return new BankAccount(accountNumber, initialBalance, null, new InterestBearing(additionalInfo));
            default:
                return new BankAccount(accountNumber, initialBalance, null, null);
        }
    }
}
