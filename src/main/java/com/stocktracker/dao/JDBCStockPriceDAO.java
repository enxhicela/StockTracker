package com.stocktracker.dao;

import com.stocktracker.entities.StockPrice;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCStockPriceDAO implements StockPriceDAO {
    private final Connection connection;

    public JDBCStockPriceDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addStockPrice(StockPrice stockPrice) {
        String sql = "INSERT INTO stock_prices (stock_id, date, price, volume) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, stockPrice.getStockId());
            stmt.setDate(2, new java.sql.Date(stockPrice.getDate().getTime()));
            stmt.setDouble(3, stockPrice.getPrice());
            stmt.setInt(4, stockPrice.getVolume());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<StockPrice> getStockPrices(int stockId) {
        List<StockPrice> prices = new ArrayList<>();
        String sql = "SELECT * FROM stock_prices WHERE stock_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, stockId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                StockPrice price = new StockPrice(rs.getInt("id"), rs.getInt("stock_id"),
                        rs.getDate("date"), rs.getDouble("price"),
                        rs.getInt("volume"));
                prices.add(price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prices;
    }
}

