package com.mdrahman.stockbull.controller;

import com.mdrahman.stockbull.model.StockOrder;
import com.mdrahman.stockbull.service.StockOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StockOrderController {

    private final StockOrderService stockOrderService;

    @Autowired
    public StockOrderController(StockOrderService stockOrderService) {
        this.stockOrderService = stockOrderService;
    }

    // Handler method to display the order form
    @GetMapping("/placeOrder")
    public String showOrderForm(Model model) {
        model.addAttribute("stockOrder", new StockOrder());
        return "stockorder";
    }

    // Handler method to process the order form submission
    @PostMapping("/placeOrder")
    public String placeOrder(@ModelAttribute StockOrder stockOrder) {
        // Save the order using the StockOrderService
        stockOrderService.saveOrder(stockOrder);
        return "redirect:/placeOrder"; // Redirect to the order form after submission
    }
}

