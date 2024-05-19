package com.example.vstocks_01;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {

    private String username;
    private int userId;
    private double balance;
    private Map<Stock, Integer> portfolio;
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    public User(String username) {
        this.username = username;
        this.userId = getUserID(username);
        this.balance = getUserBalance();
        this.portfolio = new HashMap<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getUserId() {
        return userId;
    }

    public int getUserID(String username) {
        Connection connect = database.connectDB();
        String sql = "SELECT id FROM login_schema.users WHERE username = ?";
        int userID = -1;

        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setString(1, username);
            ResultSet result = prepare.executeQuery();
            if (result.next()) {
                userID = result.getInt("id");
            }
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userID;
    }

    public double getUserBalance() {
        Connection connect = database.connectDB();
        String sql = "SELECT balance FROM login_schema.user_shares WHERE user_id = ?";
        double balance = 0;

        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setInt(1, userId);
            ResultSet result = prepare.executeQuery();
            if (result.next()) {
                balance = result.getDouble("balance");
            }
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return balance;
    }

    public int getStockQuantity(String symbol) {
        Connection connect = database.connectDB();
        String sql = "SELECT quantity FROM login_schema.user_shares WHERE user_id = ? AND symbol = ?";
        int quantity = 0;

        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setInt(1, userId);
            prepare.setString(2, symbol);
            ResultSet result = prepare.executeQuery();
            if (result.next()) {
                quantity = result.getInt("quantity");
            }
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return quantity;
    }

    public void updateBalance(double newBalance) {
        Connection connect = database.connectDB();
        String sql = "UPDATE login_schema.user_shares SET balance = ? WHERE user_id = ?";

        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setDouble(1, newBalance);
            prepare.setInt(2, userId);
            prepare.executeUpdate();
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateStockQuantity(String symbol, int newQuantity) {
        Connection connect = database.connectDB();
        String sql = "UPDATE login_schema.user_shares SET quantity = ? WHERE user_id = ? AND symbol = ?";

        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setInt(1, newQuantity);
            prepare.setInt(2, userId);
            prepare.setString(3, symbol);
            prepare.executeUpdate();
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void recordPurchase(String symbol, int quantity) {
        Connection connect = database.connectDB();
        String sql = "INSERT INTO login_schema.user_shares (user_id, symbol, quantity, balance, purchase_timestamp) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setInt(1, userId);
            prepare.setString(2, symbol);
            prepare.setInt(3, quantity);
            prepare.setDouble(4, balance);
            prepare.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            prepare.executeUpdate();
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addTransaction(String stockSymbol, int amount, double totalPrice, TransactionType transactionType, double newBalance) {
        Connection connect = database.connectDB();
        String sql = "INSERT INTO login_schema.transaction_history (user_id, transaction_type, stock_symbol, amount, new_balance) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setInt(1, userId);
            prepare.setString(2, transactionType.name());
            prepare.setString(3, stockSymbol);
            prepare.setInt(4, amount);
            prepare.setDouble(5, newBalance);
            prepare.executeUpdate();
            connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Transaction> getTransactionHistory() {
        List<Transaction> transactions = new ArrayList<>();
        Connection connect = database.connectDB();
        String sql = "SELECT * FROM login_schema.transaction_history WHERE user_id = ?";

        try {
            PreparedStatement prepare = connect.prepareStatement(sql);
            prepare.setInt(1, userId);
            ResultSet resultSet = prepare.executeQuery();
            while (resultSet.next()) {
                int transactionId = resultSet.getInt("transaction_id");
                String transactionTypeString = resultSet.getString("transaction_type");
                TransactionType transactionType = TransactionType.valueOf(transactionTypeString);
                String stockSymbol = resultSet.getString("stock_symbol");
                int amount = resultSet.getInt("amount");
                Timestamp timestamp = resultSet.getTimestamp("timestamp");
                double newBalance = resultSet.getDouble("new_balance");

                Transaction transaction = new Transaction(transactionId, transactionType, stockSymbol, amount, timestamp, newBalance);
                transactions.add(transaction);
            }
            connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    public void sellStock(String symbol, int sellShareAmount, double sellTotalPrice) {
        double newBalance = getBalance() + sellTotalPrice;
        updateBalance(newBalance);

        int currentStockQuantity = getStockQuantity(symbol);
        if (currentStockQuantity < sellShareAmount) {
            throw new IllegalArgumentException("Not enough shares to sell");
        }

        int newStockQuantity = currentStockQuantity - sellShareAmount;
        updateStockQuantity(symbol, newStockQuantity);

        addTransaction(symbol, sellShareAmount, sellTotalPrice, TransactionType.SELL, newBalance);
    }



}
