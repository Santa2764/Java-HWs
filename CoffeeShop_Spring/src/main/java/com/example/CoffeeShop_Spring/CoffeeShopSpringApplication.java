package com.example.CoffeeShop_Spring;

import com.example.CoffeeShop_Spring.menu.StartMenu;
import com.example.CoffeeShop_Spring.service.CoffeeShopInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class CoffeeShopSpringApplication {

	@Autowired
	private CoffeeShopInitializer coffeeShopInitializer;

	@Autowired
	private StartMenu startMenu;

	public static void main(String[] args) {
		SpringApplication.run(CoffeeShopSpringApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void start() {
		coffeeShopInitializer.coffeeShopInitialize();
		startMenu.start();
	}
}
