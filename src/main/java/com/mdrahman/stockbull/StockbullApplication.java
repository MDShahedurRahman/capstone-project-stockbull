package com.mdrahman.stockbull;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StockbullApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockbullApplication.class, args);
	}

}
