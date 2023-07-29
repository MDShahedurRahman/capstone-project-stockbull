package com.mdrahman.stockbull.controller;

import com.mdrahman.stockbull.model.Stock;
import com.mdrahman.stockbull.model.StockOrder;
import com.mdrahman.stockbull.model.User;
import com.mdrahman.stockbull.service.StockAPIService;
import com.mdrahman.stockbull.service.StockOrderService;
import com.mdrahman.stockbull.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class StockOrderController {

    private final StockOrderService stockOrderService;
    private final StockService stockService;
    private final StockAPIService stockAPIService;

    @Autowired
    public StockOrderController(StockOrderService stockOrderService, StockService stockService,
                                StockAPIService stockAPIService) {
        this.stockOrderService = stockOrderService;
        this.stockService = stockService;
        this.stockAPIService = stockAPIService;
    }

    // Handler method to display the order form
    @GetMapping("/placeOrder")
    public String showOrderForm(Model model) {
        List<Stock> stocks = stockService.getAllStocks();
        model.addAttribute("stocks", stocks);
        model.addAttribute("stockOrder", new StockOrder());
        return "stockorder";
    }

    // Handler method to process the order form submission
    @PostMapping("/placeOrder")
    public String placeOrder(@ModelAttribute StockOrder stockOrder, Model model) {
        // Fetch the stock price from the StockAPIService using the stock symbol
        try {
            String stockSymbol = stockOrder.getStockSymbol(); // Use the correct getter method for stockSymbol
            double stockPrice = stockAPIService.getStockPrice(stockSymbol);
            stockOrder.setStockPrice(stockPrice);
        } catch (IOException e) {
            // Handle API call errors here, e.g., log the error
            e.printStackTrace();
            // You can also add an error message to the model to display on the form
            model.addAttribute("errorMessage", "Failed to fetch stock price. Please try again.");
            return "stockorder";
        }

        // Save the order using the StockOrderService
        stockOrderService.saveOrder(stockOrder);
        return "redirect:/orderConfirmation"; // Redirect to the order confirmation page after submission
    }

    // Handler method to display the orders for the logged-in user
    @GetMapping("/orderConfirmation")
    public String showUserOrders(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName(); // Get the logged-in user's email
        List<StockOrder> userOrders = stockOrderService.getOrdersByEmail(userEmail);
        model.addAttribute("userOrders", userOrders);

        return "showorder";
    }

    // Handler method to display all user data from the StockOrder table
    @GetMapping("/showAllOrders")
    public String showAllOrders(Model model) {
        List<StockOrder> allOrders = stockOrderService.getAllOrders();
        model.addAttribute("allOrders", allOrders);
        return "orderlist";
    }

    // Handler method to cancel the order
    @PostMapping("/order/cancel/{orderId}")
    public String cancelOrder(@PathVariable Long orderId) {
        // Call the stockOrderService to delete the order by orderId
        stockOrderService.deleteOrderById(orderId);
        // Redirect to the order list page after canceling the order
        return "redirect:/showAllOrders";
    }

    // Handler method to sell the order
    @PostMapping("/order/sell/{orderId}")
    public String sellStock(@PathVariable Long orderId) {
        // Call the stockOrderService to delete the order by orderId
        stockOrderService.deleteOrderById(orderId);
        // Redirect to the order list page after selling the stock
        return "redirect:/showAllOrders";
    }


    @PostMapping("/showAllOrders/search")
    public String searchOrderByDate(@RequestParam("searchText") String searchText, Model model) {
        List<StockOrder> searchResults;

        if (searchText.contains("@")) {
            searchResults = stockOrderService.getOrdersByEmail(searchText);
            model.addAttribute("userOrders", searchResults);
        } else {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date orderDate = sdf.parse(searchText);
                searchResults = stockOrderService.getOrdersByOrderDate(orderDate);
                model.addAttribute("userOrders", searchResults);
            } catch (ParseException e) {
                // Handle parsing exception (if the date format is incorrect)
                e.printStackTrace();
                // You can add an error message to the model to display it in the view
            }
        }
        return "showorder";
    }

}
