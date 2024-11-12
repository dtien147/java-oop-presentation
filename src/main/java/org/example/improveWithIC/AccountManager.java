package org.example.improveWithIC;

import org.example.improveWithIC.interfaces.IAccount;
import org.example.improveWithIC.interfaces.IAccountFactory;

import java.util.ArrayList;
import java.util.List;

public class AccountManager {
    private List<Customer> customers = new ArrayList<>();
    private IAccountFactory accountFactory;

    public AccountManager(IAccountFactory accountFactory) {
        this.accountFactory = accountFactory;
    }

    public IAccount createAccount(String type, String customerName, double initialBalance, double additionalInfo) {
        IAccount account = accountFactory.createAccount(type, "ACC" + System.currentTimeMillis(), initialBalance, additionalInfo);

        Customer customer = findCustomerByName(customerName);
        if (customer == null) {
            customer = new Customer(customerName);
            customers.add(customer);
        }
        customer.addAccount(account);
        return account;
    }

    public void transferFunds(IAccount accountFrom, IAccount accountTo, double amount) {
        accountFrom.withdraw(amount);
        accountTo.deposit(amount);
    }

    private Customer findCustomerByName(String name) {
        for (Customer customer : customers) {
            if (customer.getName().equals(name)) {
                return customer;
            }
        }
        return null;
    }
}
