package com.example.vstocks_01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class database {

    public static Connection connectDB (){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "kennyw");
            System.out.println("Connected to database");
            return connect;
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver Not Found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Connection Failed.");
            e.printStackTrace();
        }
        return null;
    }

    public static void insertStockData(Connection connection, String symbol, double price) {
        String sql = "INSERT INTO login_schema.stock_data (symbol, price) VALUES (?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, symbol);
            statement.setDouble(2, price);
            statement.executeUpdate();
            System.out.println("Stock data inserted successfully.");
        } catch (SQLException e) {
            System.err.println("Failed to insert stock data.");
            e.printStackTrace();
        }
    }
}
