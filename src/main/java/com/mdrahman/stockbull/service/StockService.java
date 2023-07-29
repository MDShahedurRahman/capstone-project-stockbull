// StockService.java
package com.mdrahman.stockbull.service;

import com.mdrahman.stockbull.model.Stock;

import java.util.List;

public interface StockService {

    List<Stock> getAllStocks();

    // Retrieves a specific stock by its unique ID.
    // Returns: The Stock object representing the stock with the specified ID, or null if not found.
    Stock getStockById(Long id);

    // Saves or updates a stock in the system.
    // Returns: The Stock object representing the saved or updated stock.
    Stock saveStock(Stock stock);

    // Clears all stocks from the system. (Note: The method name might be a bit misleading, as it seems like it deletes all stocks, but the implementation might have a different purpose, as it's not common to delete all stocks at once.)
    // This method might be useful in scenarios where a cleanup of stock data is required.
    void clearAllStocks();

    // Deletes a specific stock by its unique ID.
    void deleteStock(Long id);

    // Retrieves a stock based on its unique stock symbol.
    // Returns: The Stock object representing the stock with the specified symbol, or null if not found.

    Stock getStockBySymbol(String stockSymbol);
}
