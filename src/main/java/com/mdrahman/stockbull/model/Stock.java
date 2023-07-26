package com.mdrahman.stockbull.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String stockName;
    private String stockSymbol;
    private double stockPrice;

    // Fields to store historical stock data fetched from Alpha Vantage
    private String timeSeriesData;

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL)
    private List<StockOrder> orders = new ArrayList<>();

    // Convenience method to add a stock order to the stock
    public void addOrder(StockOrder order) {
        orders.add(order);
        order.setStock(this);
    }
}
