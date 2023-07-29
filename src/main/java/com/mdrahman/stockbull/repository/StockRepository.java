package com.mdrahman.stockbull.repository;

import com.mdrahman.stockbull.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    // Custom query to find all distinct stock symbols from the database
    @Query("SELECT DISTINCT stock.stockSymbol FROM Stock stock")
    List<String> findAllStockSymbols();

    // Custom query to find a stock by its symbol
    Stock findByStockSymbol(String symbol);
}
