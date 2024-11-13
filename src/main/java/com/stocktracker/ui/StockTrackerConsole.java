package com.stocktracker.ui;

import com.stocktracker.service.StockService;
import java.util.Scanner;

public class StockTrackerConsole {
    private final StockService stockService;

    public StockTrackerConsole(StockService stockService) {
        this.stockService = stockService;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add Stock");
            System.out.println("2. Display All Stocks");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addStock(scanner);
                    break;
                case 2:
                    displayStocks();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
                    break;
            }
        }
    }

    private void addStock(Scanner scanner) {
        System.out.print("Enter symbol: ");
        String symbol = scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter metadata: ");
        String metadata = scanner.nextLine();

        stockService.addStock(symbol, name, metadata);
        System.out.println("Stock added.");
    }

    private void displayStocks() {
        stockService.getAllStocks().forEach(stock ->
                System.out.println(stock.getId() + " - " + stock.getSymbol() + ": " + stock.getName())
        );
    }
}

