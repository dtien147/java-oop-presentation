package org.example.improveWithInterfaces;

import org.example.improveWithInterfaces.accounts.BankAccount;
import org.example.improveWithInterfaces.accounts.CheckingAccount;
import org.example.improveWithInterfaces.accounts.LoanAccount;
import org.example.improveWithInterfaces.accounts.SavingsAccount;
import org.example.improveWithInterfaces.interfaces.IAccount;
import org.example.improveWithInterfaces.interfaces.IAccountFactory;

public class AccountFactory implements IAccountFactory {
    @Override
    public IAccount createAccount(String type, String accountNumber, double initialBalance, double additionalInfo) {
        switch (type) {
            case "checking":
                return new CheckingAccount(accountNumber, initialBalance, additionalInfo);
            case "savings":
                return new SavingsAccount(accountNumber, initialBalance, additionalInfo);
            case "loan":
                return new LoanAccount(accountNumber, initialBalance, additionalInfo);
            default:
                return new BankAccount(accountNumber, initialBalance);
        }
    }
}
