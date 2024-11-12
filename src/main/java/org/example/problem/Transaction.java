package org.example.problem;

import java.util.Date;

public class Transaction {
    private String type;
    private double amount;
    private Date date;
    private String description;

    public Transaction(String type, double amount, Date date, String description) {
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    @Override
    public String toString() {
        return this.type + " " + this.amount + " " + this.date + " " + this.description;
    }

    // Getters and setters can be added as needed
}
