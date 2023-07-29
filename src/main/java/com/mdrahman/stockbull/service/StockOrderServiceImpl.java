package com.mdrahman.stockbull.service;

import com.mdrahman.stockbull.model.StockOrder;
import com.mdrahman.stockbull.repository.StockOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StockOrderServiceImpl implements StockOrderService {

    @Autowired
    private StockOrderRepository orderRepository;

    @Override
    public List<StockOrder> getAllOrders() {
        // Retrieves a list of all stock orders using the Spring Data JPA repository.
        return orderRepository.findAll();
    }

    @Override
    public StockOrder getOrderById(Long id) {
        // Retrieves a specific stock order by its unique ID using the Spring Data JPA repository.
        // Returns null if the order with the given ID is not found.
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public StockOrder saveOrder(StockOrder order) {
        // Saves or updates a stock order using the Spring Data JPA repository.
        // Returns the saved or updated StockOrder object.
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrderById(Long id) {
        // Deletes a stock order by its unique ID using the Spring Data JPA repository.
        orderRepository.deleteById(id);
    }

    @Override
    public List<StockOrder> getOrdersByEmail(String userEmail) {
        // Retrieves a list of stock orders associated with a specific user's email address
        // using the Spring Data JPA repository.
        return orderRepository.findByUserEmail(userEmail);
    }

    @Override
    public List<StockOrder> getOrdersByOrderDate(Date orderDate) {
        // Retrieves a list of stock orders based on a specific order date
        // using the Spring Data JPA repository.
        return orderRepository.findByOrderDate(orderDate);
    }
}
