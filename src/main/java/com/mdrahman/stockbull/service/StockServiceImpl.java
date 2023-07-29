package com.mdrahman.stockbull.service;

import com.mdrahman.stockbull.model.Stock;
import com.mdrahman.stockbull.repository.StockRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;

    // Initialization method that runs after the bean is constructed.
    // This method can be used to perform additional setup or initialization tasks for the service.
    @PostConstruct
    private void init() {
        // Perform any initialization tasks here if needed.
    }

    @Override
    public List<Stock> getAllStocks() {
        // Retrieve all stocks from the database using the stock repository.
        return stockRepository.findAll();
    }

    @Override
    public Stock getStockById(Long id) {
        // Retrieve a specific stock by its unique ID using the stock repository.
        // Return null if the stock with the given ID is not found.
        return stockRepository.findById(id).orElse(null);
    }

    @Override
    public Stock getStockBySymbol(String stockSymbol) {
        // Retrieve a stock based on its unique stock symbol using the stock repository.
        return stockRepository.findByStockSymbol(stockSymbol);
    }

    @Override
    public Stock saveStock(Stock stock) {
        // Save the stock entity to the database using the stock repository.
        // The stock repository's save() method will either insert a new stock if it doesn't exist or update an existing one if found.
        Stock savedStock = stockRepository.save(stock);

        // Return the saved stock entity.
        return savedStock;
    }

    @Override
    public void deleteStock(Long id) {
        // Delete a specific stock by its unique ID using the stock repository.
        stockRepository.deleteById(id);
    }

    @Override
    public void clearAllStocks() {
        // Delete all stocks from the database using the stock repository.
        stockRepository.deleteAll();
    }
}
