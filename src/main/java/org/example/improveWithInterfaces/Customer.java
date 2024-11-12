package org.example.improveWithInterfaces;

import org.example.improveWithInterfaces.interfaces.IAccount;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private List<IAccount> accounts = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addAccount(IAccount account) {
        accounts.add(account);
    }

    public String getName() {
        return name;
    }
}
