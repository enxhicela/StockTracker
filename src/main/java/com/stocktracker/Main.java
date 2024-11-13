package com.stocktracker;

import com.stocktracker.dao.JDBCStockDAO;
import com.stocktracker.service.StockService;
import com.stocktracker.ui.StockTrackerConsole;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock_db", "root", "root1234@");
            JDBCStockDAO stockDAO = new JDBCStockDAO(connection);
            StockService stockService = new StockService(stockDAO);
            StockTrackerConsole console = new StockTrackerConsole(stockService);
            console.start();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
