package com.example.vstocks_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class YahooAPI {

    private static final String API_KEY = "976f2b63a0msh65bbe5938c8e50ap117781jsnfc0a2b6254ad";
    private static final String API_HOST = "yahoo-finance127.p.rapidapi.com";

    public static void main(String[] args) {
        try {
            URL url = new URL("https://" + API_HOST + "/search/aa");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            connection.setRequestProperty("X-RapidAPI-Key", API_KEY);
            connection.setRequestProperty("X-RapidAPI-Host", API_HOST);

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            System.out.println(response);

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
