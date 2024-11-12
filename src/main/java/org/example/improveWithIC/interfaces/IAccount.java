package org.example.improveWithIC.interfaces;

import org.example.improveWithIC.Transaction;

import java.util.List;

public interface IAccount {
    void deposit(double amount);

    void withdraw(double amount);

    List<Transaction> getTransactionHistory();
}

