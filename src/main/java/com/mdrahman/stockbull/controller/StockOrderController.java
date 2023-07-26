package com.mdrahman.stockbull.controller;

import com.mdrahman.stockbull.model.Stock;
import com.mdrahman.stockbull.model.StockOrder;
import com.mdrahman.stockbull.service.StockOrderService;
import com.mdrahman.stockbull.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StockOrderController {

    private final StockOrderService stockOrderService;
    private final StockService stockService;

    @Autowired
    public StockOrderController(StockOrderService stockOrderService, StockService stockService) {
        this.stockOrderService = stockOrderService;
        this.stockService = stockService;
    }

    // Handler method to display the order form
    @GetMapping("/placeOrder")
    public String showOrderForm(Model model) {
        model.addAttribute("stockOrder", new StockOrder());
        model.addAttribute("stocks", stockService.getAllStocks()); // Add list of stocks to the model
        return "stockorder";
    }

    // Handler method to process the order form submission
    @PostMapping("/placeOrder")
    public String placeOrder(@ModelAttribute StockOrder stockOrder) {
        // Fetch the stock object from the database using the stock symbol
        String stockSymbol = stockOrder.getStock().getStockSymbol();
        Stock stock = stockService.getStockBySymbol(stockSymbol);

        // Set the stock object in the stockOrder
        stockOrder.setStock(stock);

        // Save the order using the StockOrderService
        stockOrderService.saveOrder(stockOrder);
        return "redirect:/placeOrder"; // Redirect to the order form after submission
    }
}