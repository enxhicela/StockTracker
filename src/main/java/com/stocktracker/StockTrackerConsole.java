package com.stocktracker;

import com.stocktracker.service.Stock;
import com.stocktracker.service.PriceService;

import java.util.List;
import java.util.Scanner;

public class StockTrackerConsole {
    private final Scanner scanner;
    private final PriceService stockService;

    public StockTrackerConsole(PriceService stockService) {
        this.scanner = new Scanner(System.in);
        this.stockService = stockService;
    }

    public void start() {
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Add stock");
            System.out.println("2. Display stocks");
            System.out.println("3. Fetch and display average price");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addStock();
                    break;
                case 2:
                    displayStocks();
                    break;
                case 3:
                    fetchAndDisplayAveragePrice();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void fetchAndDisplayAveragePrice() {
        System.out.print("Enter stock symbol: ");
        String symbol = scanner.nextLine();
        System.out.print("Enter interval (e.g., 5min, daily): ");
        String interval = scanner.nextLine();

        double averagePrice = stockService.fetchAndSaveAveragePrice(symbol, interval);
        if (averagePrice != -1) {
            System.out.println("The average price for " + symbol + " is: " + averagePrice);
        }
    }

    private void addStock() {
        System.out.print("Enter stock symbol: ");
        String symbol = scanner.nextLine();
        System.out.print("Enter stock name: ");
        String name = scanner.nextLine();
        System.out.print("Enter any additional metadata (optional): ");
        String metadata = scanner.nextLine();

        Stock stock = new Stock(symbol, name, metadata);
        boolean success = stockService.addStock(stock);

        if (success) {
            System.out.println("Stock added successfully!");
        } else {
            System.out.println("Failed to add stock. It may already exist.");
        }
    }

    private void displayStocks() {
        List<Stock> stocks = stockService.getAllStocks();

        if (stocks.isEmpty()) {
            System.out.println("No stocks are currently tracked.");
        } else {
            System.out.println("Tracked Stocks:");
            for (Stock stock : stocks) {
                System.out.println("Symbol: " + stock.getSymbol() + ", Name: " + stock.getName() + ", Metadata: " + stock.getMetadata());
            }
        }
    }
}
