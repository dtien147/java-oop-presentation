package org.example.improveWithInterfaces.interfaces;

public interface IAccountFactory {
    IAccount createAccount(String type, String accountNumber, double initialBalance, double additionalInfo);
}
