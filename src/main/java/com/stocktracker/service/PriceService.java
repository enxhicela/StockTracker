package com.stocktracker.service;

import com.stocktracker.dao.StockDAO;
import com.stocktracker.entities.Stock;
import com.stocktracker.service.StockPriceFetcher;
import com.stocktracker.dao.StockPriceDAO;
import com.stocktracker.entities.StockPrice;
import org.json.JSONObject;

import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.Iterator;

public class PriceService {
    private final StockPriceDAO stockPriceDAO;
    private final StockDAO stockDAO;

    public PriceService(StockPriceDAO stockPriceDAO, StockDAO stockDAO) {
        this.stockPriceDAO = stockPriceDAO;
        this.stockDAO = stockDAO;
    }

    public void addStockPrice(int stockId, Date date, double price, int volume) {
        StockPrice stockPrice = new StockPrice(0, stockId, date, price, volume);
        stockPriceDAO.addStockPrice(stockPrice);
    }

    public List<StockPrice> getStockPrices(int stockId) {
        return stockPriceDAO.getStockPrices(stockId);
    }

    public double fetchAndSaveAveragePrice(String symbol, String interval) {
        String stockData = StockPriceFetcher.fetchStockData(symbol, interval);
        if (stockData == null) {
            System.out.println("Failed to fetch stock data.");
            return -1;
        }

        try {
            JSONObject jsonObject = new JSONObject(stockData);
            JSONObject timeSeries = jsonObject.getJSONObject("Time Series (5min)");
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

            double averagePrice = sum / count;
            System.out.println("Average Price: " + averagePrice);

            Date currentDate = new Date();
            int stockId = getStockId(symbol);
            if (stockId != -1) {
                addStockPrice(stockId, currentDate, averagePrice, 0);
            } else {
                System.out.println("Stock with symbol " + symbol + " not found.");
            }

            return averagePrice;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public boolean addStock(Stock stock) {
        // Check if stock already exists in the database
        if (stockDAO.getStockBySymbol(stock.getSymbol()) != null) {
            System.out.println("Stock with symbol " + stock.getSymbol() + " already exists.");
            return false;
        }

        return stockDAO.addStock(stock);
    }

    public List<Stock> getAllStocks() {
        return stockDAO.getAllStocks();
    }

    private int getStockId(String symbol) {
        Stock stock = stockDAO.getStockBySymbol(symbol);
        return stock != null ? stock.getId() : -1;
    }
}

