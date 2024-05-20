package com.example.vstocks_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class YahooAPI {

    private static final String API_KEY = "976f2b63a0msh65bbe5938c8e50ap117781jsnfc0a2b6254ad";
    private static final String API_HOST = "yahoo-finance127.p.rapidapi.com";

    public static void main(String[] args) {
        YahooAPI api = new YahooAPI();
        List<String> symbols = List.of("AA", "AAPL", "AAL", "AAOI", "AAP", "AAON", "AABB",
                "MSFT", "GOOGL", "AMZN");

        //String testSymbol = symbols.getFirst();
        try {
            Map<String, Double> symbolPrices = api.getStockPrices(symbols);
            Connection connection = database.connectDB();
            if (connection != null) {
                for (Map.Entry<String, Double> entry : symbolPrices.entrySet()) {
                    String symbol = entry.getKey();
                    double price = entry.getValue();
                    System.out.println("Symbol: " + symbol + " | Price: $" + price);
                    database.insertStockData(connection, symbol, price);
                }
                try {
                    connection.close();
                    System.out.println("Database connection closed.");
                } catch (SQLException e) {
                    System.err.println("Failed to close database connection.");
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.err.println("Error retrieving stock prices.");
            e.printStackTrace();
        }

    }

    public Map<String, Double> getStockPrices(List<String> symbols) throws IOException {
        Map<String, Double> prices = new HashMap<>();
        for (String symbol : symbols) {
            double price = getPriceForSymbol(symbol);
            prices.put(symbol, price);
        }
        return prices;
    }

    private double getPriceForSymbol(String symbol) throws IOException {
        String urlString = "https://" + API_HOST + "/price/" + symbol;
        URL url = new URL(urlString);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("X-RapidAPI-Key", API_KEY);
        connection.setRequestProperty("X-RapidAPI-Host", API_HOST);

        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new IOException("HTTP error code: " + responseCode);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        connection.disconnect();

        String responseBody = response.toString();
        System.out.println("Response for symbol " + symbol + ": " + responseBody);

        JSONObject jsonResponse = new JSONObject(responseBody);

        if (!jsonResponse.has("regularMarketPrice")) {
            throw new IOException("regularMarketPrice not found in the response for symbol: " + symbol);
        }

        JSONObject regularMarketPrice = jsonResponse.getJSONObject("regularMarketPrice");
        if (!regularMarketPrice.has("raw")) {
            throw new IOException("regularMarketPrice.raw not found in the response for symbol: " + symbol);
        }

        return regularMarketPrice.getDouble("raw");
    }
}
