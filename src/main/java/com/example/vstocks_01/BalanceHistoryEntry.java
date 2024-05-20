package com.example.vstocks_01;

import java.time.LocalDateTime;

public class BalanceHistoryEntry {
    private double balance;
    private LocalDateTime timestamp;

    public BalanceHistoryEntry(double balance, LocalDateTime timestamp) {
        this.balance = balance;
        this.timestamp = timestamp;
    }

    public double getBalance() {
        return balance;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
