package com.ekinox.bookshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookshopApplication implements ApplicationRunner {
	@Autowired
	PriceCalculatorService priceCalculatorService;
	public static void main(String[] args) {
		SpringApplication.run(BookshopApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) {
		priceCalculatorService.price("cart.txt");
	}
}
