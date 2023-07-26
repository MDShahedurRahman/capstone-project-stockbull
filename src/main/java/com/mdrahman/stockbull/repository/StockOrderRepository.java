package com.mdrahman.stockbull.repository;

import com.mdrahman.stockbull.model.StockOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockOrderRepository extends JpaRepository<StockOrder, Long> {

}

