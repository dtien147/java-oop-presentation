package org.example.improveWithInterfaces;

import org.example.improveWithInterfaces.accounts.BankAccount;
import org.example.improveWithInterfaces.interfaces.IAccount;
import org.example.improveWithInterfaces.interfaces.IAccountFactory;
import org.example.improveWithInterfaces.interfaces.IInterestBearingAccount;

public class Main {
    public static void main(String[] args) {
        IAccountFactory accountFactory = new AccountFactory();
        AccountManager accountManager = new AccountManager(accountFactory);

        // Create different types of accounts
        IAccount checkingAccount = accountManager.createAccount("checking", "Alice", 500, 200); // overdraft limit of 200
        IAccount savingsAccount = accountManager.createAccount("savings", "Alice", 1000, 0.03); // interest rate of 0.03
        IAccount loanAccount = accountManager.createAccount("loan", "Bob", 5000, 0.07); // interest rate of 0.07

        // Perform transactions
        checkingAccount.deposit(200);
        checkingAccount.withdraw(300);

        // Calculate interest if the account is interest-bearing0111111
        if (savingsAccount instanceof IInterestBearingAccount) {
            ((IInterestBearingAccount) savingsAccount).calculateInterest();
        }

        // Transfer funds
        accountManager.transferFunds(checkingAccount, savingsAccount, 100);

        // Display transaction history
        displayTransactions(checkingAccount);
        displayTransactions(savingsAccount);
        displayTransactions(loanAccount);
    }

    private static void displayTransactions(IAccount account) {
        System.out.println("Transaction history for account: " + ((BankAccount) account).getAccountNumber());
        for (Transaction transaction : account.getTransactionHistory()) {
            System.out.println(transaction.toString());
        }
    }
}
