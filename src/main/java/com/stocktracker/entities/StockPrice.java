package com.stocktracker.entities;

import java.util.Date;

public class StockPrice {
    private int id;
    private int stockId;
    private Date date;
    private double price;
    private int volume;

    public StockPrice(int id, int stockId, Date date, double price, int volume) {
        this.id = id;
        this.stockId = stockId;
        this.date = date;
        this.price = price;
        this.volume = volume;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getStockId() {
        return stockId;
    }
    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public int getVolume() {
        return volume;
    }
    public void setVolume(int volume) {
        this.volume = volume;
    }
}
