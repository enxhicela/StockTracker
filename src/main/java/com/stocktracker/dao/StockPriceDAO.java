package com.stocktracker.dao;

import com.stocktracker.entities.StockPrice;
import java.util.List;

public interface StockPriceDAO {
    void addStockPrice(StockPrice stockPrice);
    List<StockPrice> getStockPrices(int stockId);
}

