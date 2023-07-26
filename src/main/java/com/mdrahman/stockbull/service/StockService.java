// StockService.java
package com.mdrahman.stockbull.service;

import com.mdrahman.stockbull.model.Stock;

import java.util.List;

public interface StockService {
    List<Stock> getAllStocks();
    Stock getStockById(Long id);
    Stock saveStock(Stock stock);
    
    void deleteStock(Long id);

    Stock getStockBySymbol(String stockSymbol);
}


