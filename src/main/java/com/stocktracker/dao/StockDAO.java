package com.stocktracker.dao;

import com.stocktracker.entities.Stock;
import java.util.List;

public interface StockDAO {
    boolean addStock(Stock stock);
    List<Stock> getAllStocks();
    Stock getStockBySymbol(String symbol);
}
