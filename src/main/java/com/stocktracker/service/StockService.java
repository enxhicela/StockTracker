package com.stocktracker.service;

import com.stocktracker.dao.StockDAO;

import java.util.List;

public class StockService {
    private final StockDAO stockDAO;

    public StockService(StockDAO stockDAO) {
        this.stockDAO = stockDAO;
    }

    public void addStock(String symbol, String name, String metadata) {
        Stock stock = new Stock(symbol, name, metadata);
        stockDAO.addStock(stock);
    }

    public List<Stock> getAllStocks() {
        return stockDAO.getAllStocks();
    }

    public Stock getStockBySymbol(String symbol) {
        return stockDAO.getStockBySymbol(symbol);
    }
}

