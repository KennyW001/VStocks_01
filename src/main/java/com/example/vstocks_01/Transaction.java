package com.example.vstocks_01;

import java.sql.Timestamp;

public class Transaction {
    private int transactionId;
    private TransactionType transactionType;
    private String stockSymbol;
    private int amount;
    private Timestamp timestamp;
    private double newBalance;

    public Transaction(int transactionId, TransactionType transactionType, String stockSymbol, int amount, Timestamp timestamp, double newBalance) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.stockSymbol = stockSymbol;
        this.amount = amount;
        this.timestamp = timestamp;
        this.newBalance = newBalance;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public double getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(double newBalance) {
        this.newBalance = newBalance;
    }
}
