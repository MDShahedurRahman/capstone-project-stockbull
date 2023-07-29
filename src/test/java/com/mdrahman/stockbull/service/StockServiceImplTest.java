package com.mdrahman.stockbull.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.mdrahman.stockbull.model.Stock;
import com.mdrahman.stockbull.repository.StockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class StockServiceImplTest {

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockServiceImpl stockService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetStockBySymbol_StockExists() {
        // Mock the behavior of the stockRepository.findByStockSymbol() method
        String stockSymbol = "AAPL";
        Stock mockStock = new Stock(1L, "Apple Inc.", stockSymbol, 150.0);
        when(stockRepository.findByStockSymbol(stockSymbol)).thenReturn(mockStock);

        // Call the method under test
        Stock result = stockService.getStockBySymbol(stockSymbol);

        // Verify the result
        assertNotNull(result);
        assertEquals(mockStock.getId(), result.getId());
        assertEquals(mockStock.getStockName(), result.getStockName());
        assertEquals(mockStock.getStockSymbol(), result.getStockSymbol());
        assertEquals(mockStock.getStockPrice(), result.getStockPrice(), 0.001);
    }

    @Test
    public void testGetStockBySymbol_StockNotExists() {
        // Mock the behavior of the stockRepository.findByStockSymbol() method
        String stockSymbol = "GOOG";
        when(stockRepository.findByStockSymbol(stockSymbol)).thenReturn(null);

        // Call the method under test
        Stock result = stockService.getStockBySymbol(stockSymbol);

        // Verify the result
        assertNull(result);
    }
}
