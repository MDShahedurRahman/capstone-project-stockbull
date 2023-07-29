package com.mdrahman.stockbull.repository;

import com.mdrahman.stockbull.model.StockOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface StockOrderRepository extends JpaRepository<StockOrder, Long> {

    // Custom query to find all stock orders by user email
    List<StockOrder> findByUserEmail(String userEmail);

    // Custom query to find all stock orders by order date
    List<StockOrder> findByOrderDate(Date orderDate);

    // Custom query to find all stock orders by stock symbol
    List<StockOrder> findByStockSymbol(String stockSymbol);

    // Custom query to get the stock price by stock symbol
    double getStockPriceByStockSymbol(String stockSymbol);
}
