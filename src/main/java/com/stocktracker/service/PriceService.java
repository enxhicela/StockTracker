package com.stocktracker.service;

import com.stocktracker.service.StockPriceFetcher;
import com.stocktracker.dao.StockPriceDAO;
import com.stocktracker.entities.StockPrice;
import org.json.JSONObject;
import org.json.JSONArray;

import java.util.Date;
import java.util.List;
import java.util.Iterator;

public class PriceService {
    private final StockPriceDAO stockPriceDAO;

    public PriceService(StockPriceDAO stockPriceDAO) {
        this.stockPriceDAO = stockPriceDAO;
    }

    public void addStockPrice(int stockId, Date date, double price, int volume) {
        StockPrice stockPrice = new StockPrice(0, stockId, date, price, volume);
        stockPriceDAO.addStockPrice(stockPrice);
    }

    public List<StockPrice> getStockPrices(int stockId) {
        return stockPriceDAO.getStockPrices(stockId);
    }

    public double fetchAndSaveAveragePrice(String symbol, String interval) {
        // Fetch the JSON data from Alpha Vantage API
        String stockData = StockPriceFetcher.fetchStockData(symbol, interval);
        if (stockData == null) {
            System.out.println("Failed to fetch stock data.");
            return -1;
        }

        // Parse JSON to extract daily closing prices and calculate average
        try {
            JSONObject jsonObject = new JSONObject(stockData);
            JSONObject timeSeries = jsonObject.getJSONObject("Time Series (5min)"); // Use appropriate key for the interval
            Iterator<String> keys = timeSeries.keys();

            double sum = 0.0;
            int count = 0;

            while (keys.hasNext()) {
                String key = keys.next();
                JSONObject dailyData = timeSeries.getJSONObject(key);
                double closePrice = dailyData.getDouble("4. close");
                sum += closePrice;
                count++;
            }

            // Calculate average
            double averagePrice = sum / count;
            System.out.println("Average Price: " + averagePrice);

            // Save the average price to the database
            Date currentDate = new Date();
            addStockPrice(getStockId(symbol), currentDate, averagePrice, 0); // Save with stockId, date, and average price

            return averagePrice;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    // A method to get stock ID based on symbol (you would implement this)
    private int getStockId(String symbol) {
        // Implement this to retrieve stock ID from database based on symbol
        return 1; // Placeholder: replace with actual retrieval
    }
}
