package com.mdrahman.stockbull.repository;

import com.mdrahman.stockbull.model.StockOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface StockOrderRepository extends JpaRepository<StockOrder, Long> {

    List<StockOrder> findByUserEmail(String userEmail);
    List<StockOrder> findByOrderDate(Date orderDate);
    List<StockOrder> findByStockSymbol(String stockSymbol);

    double getStockPriceByStockSymbol(String stockSymbol);
}

