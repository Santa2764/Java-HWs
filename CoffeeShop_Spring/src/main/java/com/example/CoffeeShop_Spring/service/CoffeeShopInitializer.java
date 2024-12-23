package com.example.CoffeeShop_Spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoffeeShopInitializer {

    @Autowired
    private CoffeeShopDbInitializer coffeeShopDbInitializer;

    public void coffeeShopInitialize() {
        System.out.println("-".repeat(20));

        coffeeShopDbInitializer.createTables();
        coffeeShopDbInitializer.deleteAllRowsInDB();
        coffeeShopDbInitializer.createTypeAssortements();
        coffeeShopDbInitializer.createAssortements();
        coffeeShopDbInitializer.createRandomClients();
        coffeeShopDbInitializer.createRandomOrders();
        coffeeShopDbInitializer.createRandomOrderItems();
        coffeeShopDbInitializer.createPersonalPosition();
        coffeeShopDbInitializer.createRandomPersonal();
        coffeeShopDbInitializer.createPersonalSchedule();

        System.out.println("-".repeat(20));
    }
}
