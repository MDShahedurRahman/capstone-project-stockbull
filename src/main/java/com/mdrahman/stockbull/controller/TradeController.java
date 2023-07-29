package com.mdrahman.stockbull.controller;

import com.mdrahman.stockbull.model.StockOrder;
import com.mdrahman.stockbull.repository.StockOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Optional;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class TradeController {

    private final Logger logger = LoggerFactory.getLogger(TradeController.class);

    private final JdbcTemplate jdbcTemplate;

    private final StockOrderRepository stockOrderRepository;

    public TradeController(StockOrderRepository stockOrderRepository, JdbcTemplate jdbcTemplate) {
        this.stockOrderRepository = stockOrderRepository;
        this.jdbcTemplate = jdbcTemplate;
    }


    @GetMapping("/trade")
    public String tradePage(@RequestParam String stockSymbol,
                            @RequestParam double currentPrice,
                            @RequestParam double investedAmount,
                            Model model) {
        Double investedStockPrice = 1.0; //Just a placeholder. This calculation will be done later

        // Calculate Total Equity based on the current updated price
        double totalEquity = currentPrice/investedStockPrice * investedAmount;

        // Pass the necessary attributes to the template
        model.addAttribute("stockSymbol", stockSymbol);
        model.addAttribute("currentPrice", currentPrice);
        model.addAttribute("investedAmount", investedAmount);
        model.addAttribute("totalEquity", totalEquity);

        return "trade";
    }



    @DeleteMapping("/sell")
    public String sellOrder(@RequestParam Long orderId) {
        logger.info("Received sell request for orderId: {}", orderId);
        // Retrieve the order from the database using the orderId
        Optional<StockOrder> orderOptional = stockOrderRepository.findById(orderId);

        // Check if the order exists in the database
        if (orderOptional.isPresent()) {
            // If the order exists, delete it from the database
            stockOrderRepository.delete(orderOptional.get());
            // Redirect to the order confirmation page after selling
            return "redirect:/orderConfirmation";
        } else {
            // If the order does not exist, handle the error or redirect to an error page
            return "redirect:/error"; // For example, redirect to an error page
        }
    }

}
