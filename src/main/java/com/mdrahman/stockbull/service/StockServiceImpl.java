// StockServiceImpl.java
package com.mdrahman.stockbull.service;

import com.mdrahman.stockbull.model.Stock;
import com.mdrahman.stockbull.repository.StockRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;


    @Override
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    @Override
    public Stock getStockById(Long id) {
        return stockRepository.findById(id).orElse(null);
    }

    @Override
    public Stock saveStock(Stock stock) {
        // Save the stock entity to the database
        Stock savedStock = stockRepository.save(stock);


        return savedStock;
    }

    @Override
    public void deleteStock(Long id) {
        stockRepository.deleteById(id);
    }


}
