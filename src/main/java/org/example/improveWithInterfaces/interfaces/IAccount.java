package org.example.improveWithInterfaces.interfaces;

import org.example.improveWithInterfaces.Transaction;

import java.util.List;

public interface IAccount {
    void deposit(double amount);

    void withdraw(double amount);

    List<Transaction> getTransactionHistory();
}

