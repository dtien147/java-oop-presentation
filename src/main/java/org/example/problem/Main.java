package org.example.problem;

import org.example.problem.accounts.BankAccount;
import org.example.problem.accounts.SavingsAccount;

public class Main {
    public static void main(String[] args) {
        AccountManager accountManager = new AccountManager();

        // Create different types of accounts
        BankAccount checkingAccount = accountManager.createAccount("checking", "Alice", 500, 200); // 200 as overdraft limit
        BankAccount savingsAccount = accountManager.createAccount("savings", "Alice", 1000, 0.03); // 0.03 as interest rate
        BankAccount loanAccount = accountManager.createAccount("loan", "Bob", 5000, 0.07); // 0.07 as loan interest rate

        // Perform transactions
        checkingAccount.deposit(200);
        checkingAccount.withdraw(300);

        // Cast to SavingsAccount to access interest calculation
        if (savingsAccount instanceof SavingsAccount) {
            ((SavingsAccount) savingsAccount).calculateInterest();
        }

        // Transfer funds
        accountManager.transferFunds(checkingAccount, savingsAccount, 100);

        // Display transaction history
        displayTransactions(checkingAccount);
        displayTransactions(savingsAccount);
        displayTransactions(loanAccount);
    }

    private static void displayTransactions(BankAccount account) {
        System.out.println("Transaction history for account: " + account.getAccountNumber());
        for (Transaction transaction : account.getTransactionHistory()) {
            System.out.println(transaction.toString());
        }
    }
}
