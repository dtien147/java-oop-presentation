package org.example.problem;

import org.example.problem.accounts.BankAccount;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private List<BankAccount> accounts = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addAccount(BankAccount account) {
        accounts.add(account);
    }

    public String getName() {
        return name;
    }
}
