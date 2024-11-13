package com.stocktracker;

import com.stocktracker.service.PriceService;
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
            scanner.nextLine(); // Consume newline

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
    }

    private void displayStocks() {

    }
}
