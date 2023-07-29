package com.mdrahman.stockbull.service;

import com.mdrahman.stockbull.model.StockOrder;

import java.util.Date;
import java.util.List;

/**
 * The StockOrderService interface defines the contract for the service that manages stock orders.
 * It declares several methods that provide CRUD (Create, Read, Update, Delete) operations for stock orders,
 * as well as methods to retrieve orders based on specific criteria.
 */
public interface StockOrderService {

    /**
     * Retrieves a list of all stock orders in the system.
     *
     * @return A list of StockOrder objects representing all the stock orders in the system.
     */
    List<StockOrder> getAllOrders();

    /**
     * Retrieves a specific stock order by its unique ID.
     *
     * @param id The unique ID of the stock order to retrieve.
     * @return The StockOrder object corresponding to the given ID, or null if not found.
     */
    StockOrder getOrderById(Long id);

    /**
     * Saves or updates a stock order in the system.
     *
     * @param order The StockOrder object to be saved or updated.
     * @return The saved or updated StockOrder object.
     */
    StockOrder saveOrder(StockOrder order);

    /**
     * Deletes a stock order by its unique ID.
     *
     * @param id The unique ID of the stock order to delete.
     */
    void deleteOrderById(Long id);

    /**
     * Retrieves a list of stock orders associated with a specific user's email address.
     *
     * @param userEmail The email address of the user to retrieve stock orders for.
     * @return A list of StockOrder objects associated with the given email address.
     */
    List<StockOrder> getOrdersByEmail(String userEmail);

    /**
     * Retrieves a list of stock orders based on a specific order date.
     *
     * @param orderDate The date of the stock orders to retrieve.
     * @return A list of StockOrder objects with orders placed on the given date.
     */
    List<StockOrder> getOrdersByOrderDate(Date orderDate);
}

