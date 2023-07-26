package com.mdrahman.stockbull.repository;

import com.mdrahman.stockbull.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    Stock findByStockSymbol(String symbol);
}

