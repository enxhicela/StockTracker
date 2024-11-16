package com.stocktracker.dao;

import com.stocktracker.service.Stock;
import java.util.List;

public interface StockDAO {
    boolean addStock(Stock stock);
    List<Stock> getAllStocks();
    Stock getStockBySymbol(String symbol);
}
