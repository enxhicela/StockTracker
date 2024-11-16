package com.stocktracker;

import com.stocktracker.dao.JDBCStockDAO;
import com.stocktracker.service.PriceService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock_db",
                    "root", "root1234@");
            JDBCStockDAO stockDAO = new JDBCStockDAO(connection);
            PriceService priceService = new PriceService(stockDAO); // Correct service
            StockTrackerConsole console = new StockTrackerConsole(priceService);
            console.start();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
