package com.example.vstocks_01;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import yahoofinance.Stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StockDataRetriever {

    public ObservableList<Stock> getAllStocks() {
        ObservableList<Stock> stocks = FXCollections.observableArrayList();
        String query = "SELECT * FROM stock_data";

        try (Connection connection = database.connectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String symbol = resultSet.getString("symbol");
                double price = resultSet.getDouble("price");

                Stock stock = new Stock(symbol);
                stocks.add(stock);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving stock data: " + e.getMessage());
            e.printStackTrace();
        }

        return stocks;
    }
}
