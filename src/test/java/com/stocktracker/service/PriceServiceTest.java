package com.stocktracker.service;

import com.stocktracker.dao.StockDAO;
import com.stocktracker.dao.StockPriceDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PriceServiceTest {

    private PriceService priceService;
    private StockDAO stockDAO;

    @BeforeEach
    void setUp() {
        stockDAO = (StockDAO) mock(StockPriceDAO.class); // Mock the correct DAO type
        priceService = new PriceService(stockDAO);
    }

    @Test
    void testAddStock_Success() {
        Stock stock = new Stock("AAPL", "Apple Inc.", "Tech company");

        // Mock behavior for DAO
        when(stockDAO.getStockBySymbol("AAPL")).thenReturn(null); // No duplicate stock
        when(stockDAO.addStock(stock)).thenReturn(true); // Simulate successful insert

        // Test the method
        boolean result = priceService.addStock(stock);

        // Verify behavior and assert
        verify(stockDAO).getStockBySymbol("AAPL");
        verify(stockDAO).addStock(stock);
        assertTrue(result, "Stock should be added successfully.");
    }

    @Test
    void testGetAllStocks() {

        List<Stock> mockStocks = Arrays.asList(
                new Stock("AAPL", "Apple Inc.", "Tech company"),
                new Stock("GOOGL", "Alphabet Inc.", "Parent of Google")
        );
        when(stockDAO.getAllStocks()).thenReturn(mockStocks);


        List<Stock> stocks = priceService.getAllStocks();

        verify(stockDAO).getAllStocks();
        assertNotNull(stocks, "Stock list should not be null.");
        assertEquals(2, stocks.size(), "There should be two stocks in the list.");
        assertEquals("AAPL", stocks.get(0).getSymbol(), "First stock symbol should be 'AAPL'.");
    }
}
