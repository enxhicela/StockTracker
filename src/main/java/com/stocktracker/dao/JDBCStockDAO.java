package com.stocktracker.dao;

import com.stocktracker.entities.Stock;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCStockDAO implements StockDAO {
    private final Connection connection;

    public JDBCStockDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean addStock(Stock stock) {
        String sql = "INSERT INTO stocks (symbol, name, metadata) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, stock.getSymbol());
            stmt.setString(2, stock.getName());
            stmt.setString(3, stock.getMetadata());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Stock> getAllStocks() {
        List<Stock> stocks = new ArrayList<>();
        String sql = "SELECT * FROM stocks";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Stock stock = new Stock(rs.getString("symbol"),
                        rs.getString("name"), rs.getString("metadata"));
                stocks.add(stock);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stocks;
    }

    @Override
    public Stock getStockBySymbol(String symbol) {
        Stock stock = null;
        String sql = "SELECT * FROM stocks WHERE symbol = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, symbol);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                stock = new Stock(rs.getString("symbol"),
                        rs.getString("name"), rs.getString("metadata"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stock;
    }
}
