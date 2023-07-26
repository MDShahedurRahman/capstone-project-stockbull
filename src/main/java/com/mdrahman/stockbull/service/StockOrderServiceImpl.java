package com.mdrahman.stockbull.service;

import com.mdrahman.stockbull.model.StockOrder;
import com.mdrahman.stockbull.repository.StockOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}

