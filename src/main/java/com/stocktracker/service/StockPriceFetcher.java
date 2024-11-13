package com.stocktracker.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class StockPriceFetcher {
    private static final String API_KEY = "RDNUPIYTBMB9AR4Z";
    private static final String BASE_URL = "https://www.alphavantage.co/query";

    public static String fetchStockData(String symbol, String interval) {
        String function = "TIME_SERIES_INTRADAY";
        String urlString = String.format("%s?function=%s&symbol=%s&interval=%s&apikey=%s",
                BASE_URL, function, symbol, interval, API_KEY);

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            return response.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
