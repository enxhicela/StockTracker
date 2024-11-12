package com.stocktracker.entities;

public class Stock {
    private int id;
    private String symbol;
    private String name;
    private String metadata;

    public Stock(int id, String symbol, String name, String metadata) {
        this.id = id;
        this.symbol = symbol;
        this.name = name;
        this.metadata = metadata;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getMetadata() {
        return metadata;
    }
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }
}
