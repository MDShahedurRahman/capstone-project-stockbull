package com.mdrahman.stockbull.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.mdrahman.stockbull.model.StockOrder;
import com.mdrahman.stockbull.repository.StockOrderRepository;
import com.mdrahman.stockbull.service.StockOrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

public class StockOrderServiceImplTest {

    @Mock
    private StockOrderRepository stockOrderRepository;

    @InjectMocks
    private StockOrderServiceImpl stockOrderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveOrder() {
        // Create a mock StockOrder object
        StockOrder mockOrder = new StockOrder();
        mockOrder.setOrderDate(new Date());
        mockOrder.setStockPrice(150.0);
        mockOrder.setStockSymbol("AAPL");
        mockOrder.setInvestAmount(2000.0);
        mockOrder.setCardType("VISA");
        mockOrder.setCardNumber("1234-5678-9012-3456");
        mockOrder.setUserEmail("user@example.com");
        mockOrder.setAddress("123 Main St");
        mockOrder.setComment("Test order");

        // Mock the behavior of the stockOrderRepository.save() method
        when(stockOrderRepository.save(mockOrder)).thenReturn(mockOrder);

        // Call the method under test
        StockOrder savedOrder = stockOrderService.saveOrder(mockOrder);

        // Verify the result
        assertNotNull(savedOrder);
        assertEquals(mockOrder.getOrderDate(), savedOrder.getOrderDate());
        assertEquals(mockOrder.getStockPrice(), savedOrder.getStockPrice(), 0.001);
        assertEquals(mockOrder.getStockSymbol(), savedOrder.getStockSymbol());
        assertEquals(mockOrder.getInvestAmount(), savedOrder.getInvestAmount(), 0.001);
        assertEquals(mockOrder.getCardType(), savedOrder.getCardType());
        assertEquals(mockOrder.getCardNumber(), savedOrder.getCardNumber());
        assertEquals(mockOrder.getUserEmail(), savedOrder.getUserEmail());
        assertEquals(mockOrder.getAddress(), savedOrder.getAddress());
        assertEquals(mockOrder.getComment(), savedOrder.getComment());

        // Verify that the stockOrderRepository.save() method was called once
        verify(stockOrderRepository, times(1)).save(mockOrder);
    }
}

