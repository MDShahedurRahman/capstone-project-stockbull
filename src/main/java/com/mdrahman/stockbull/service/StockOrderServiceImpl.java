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
        return orderRepository.findAll();
    }

    @Override
    public StockOrder getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public StockOrder saveOrder(StockOrder order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public List<StockOrder> getOrdersByEmail(String userEmail) {

        return orderRepository.findByUserEmail(userEmail);
    }

    @Override
    public List<StockOrder> getOrdersByOrderDate(Date orderDate) {
        return orderRepository.findByOrderDate(orderDate);
    }

}

