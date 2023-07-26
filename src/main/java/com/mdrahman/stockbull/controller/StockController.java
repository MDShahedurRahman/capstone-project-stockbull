package com.mdrahman.stockbull.controller;

import com.mdrahman.stockbull.model.Stock;
import com.mdrahman.stockbull.service.StockAPIService;
import com.mdrahman.stockbull.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class StockController {

    @Autowired
    private StockService stockService;

    @Autowired
    private StockAPIService stockAPIService;

    @GetMapping("/create")
    public String showCreateStockForm() {
        return "create_stock";
    }

    @PostMapping("/create")
    public String createStock(@RequestParam String stockSymbol, Model model) throws IOException {
        // Get stock information from the API service
        String stockName = stockAPIService.getStockName(stockSymbol);
        double stockPrice = stockAPIService.getStockPrice(stockSymbol);

        // Create a new Stock entity and save it to the database
        Stock stock = new Stock();
        stock.setStockSymbol(stockSymbol);
        stock.setStockName(stockName);
        stock.setStockPrice(stockPrice);

        stockService.saveStock(stock);

        model.addAttribute("message", "Stock created successfully!");
        return "create_stock";
    }
}

