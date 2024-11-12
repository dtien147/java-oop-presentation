package org.example.problem;

import org.example.problem.accounts.BankAccount;
import org.example.problem.accounts.CheckingAccount;
import org.example.problem.accounts.LoanAccount;
import org.example.problem.accounts.SavingsAccount;

import java.util.ArrayList;
import java.util.List;

public class AccountManager {
    private List<Customer> customers = new ArrayList<>();

    public BankAccount createAccount(String type, String customerName, double initialBalance, double additionalInfo) {
        BankAccount account;

        switch (type) {
            case "checking":
                account = new CheckingAccount("CHK" + System.currentTimeMillis(), initialBalance, additionalInfo);
                break;
            case "savings":
                account = new SavingsAccount("SAV" + System.currentTimeMillis(), initialBalance, additionalInfo);
                break;
            case "loan":
                account = new LoanAccount("LN" + System.currentTimeMillis(), initialBalance, additionalInfo);
                break;
            default:
                account = new BankAccount("ACC" + System.currentTimeMillis(), initialBalance);
        }

        Customer customer = findCustomerByName(customerName);
        if (customer == null) {
            customer = new Customer(customerName);
            customers.add(customer);
        }
        customer.addAccount(account);
        return account;
    }

    public void transferFunds(BankAccount accountFrom, BankAccount accountTo, double amount) {
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
