package com.mdrahman.stockbull.controller;

import com.mdrahman.stockbull.model.Stock;
import com.mdrahman.stockbull.service.StockAPIService;
import com.mdrahman.stockbull.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/stocks") // Add a common prefix for all mapping endpoints related to stocks
public class StockController {

    private final StockService stockService;
    private final StockAPIService stockAPIService;

    @Autowired
    public StockController(StockService stockService, StockAPIService stockAPIService) {
        this.stockService = stockService;
        this.stockAPIService = stockAPIService;
    }

    // Handler method to display the list of stocks
    @GetMapping
    public String listStocks(Model model) {
        // Fetch all stocks from the stockService and pass them to the template
        List<Stock> stocks = stockService.getAllStocks();
        model.addAttribute("stocks", stocks);
        return "stocklist"; // Return the view name for displaying the list of stocks
    }

    // Handler method to show the form for creating a new stock
    @GetMapping("/create")
    public String showCreateStockForm(Model model) {
        // Create a new empty Stock object and pass it to the template
        model.addAttribute("stock", new Stock());
        return "createstock"; // Return the view name for displaying the create stock form
    }

    // Handler method to create a new stock
    @PostMapping("/create")
    public String createStock(@RequestParam String stockSymbol) throws IOException {
        // Fetch the stock price and name from the API using the stock symbol
        double stockPrice = stockAPIService.getStockPrice(stockSymbol);
        String stockName = stockAPIService.getStockName(stockSymbol);
        // Create a new stock object with the fetched data
        Stock stock = new Stock();
        stock.setStockSymbol(stockSymbol);
        stock.setStockPrice(stockPrice); // Set the fetched stock price
        stock.setStockName(stockName); // Set the fetched stock name

        // Save the stock to the database using the StockService
        stockService.saveStock(stock);

        return "redirect:/stocks"; // Redirect to the list of stocks after creating the stock
    }

    // Handler method to clear all stocks
    @PostMapping("/clearAll")
    public String clearAllStocks() {
        // Call the clearAllStocks method from the stockService to delete all stocks
        stockService.clearAllStocks();
        return "redirect:/stocks"; // Redirect back to the stock list page after clearing all stocks
    }
}
