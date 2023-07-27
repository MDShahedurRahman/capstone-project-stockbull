package com.mdrahman.stockbull.service;

import com.mdrahman.stockbull.model.StockOrder;
import java.util.List;

public interface StockOrderService {
    List<StockOrder> getAllOrders();
    StockOrder getOrderById(Long id);
    StockOrder saveOrder(StockOrder order);
    void deleteOrder(Long id);

    List<StockOrder> getOrdersByEmail(String userEmail);
}

