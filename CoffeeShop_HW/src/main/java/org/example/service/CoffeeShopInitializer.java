package org.example.service;

import org.example.exception.FileException;

import static java.lang.System.setProperty;

public class CoffeeShopInitializer {
    public void coffeeShopInitialize() {
        setProperty("test", "false");

        try {
            CoffeeShopDbInitializer.createTables();
            CoffeeShopDbInitializer.deleteAllRowsInDB();
            CoffeeShopDbInitializer.createTypeAssortements();
            CoffeeShopDbInitializer.createAssortements();
            CoffeeShopDbInitializer.createRandomClients();
            CoffeeShopDbInitializer.createRandomOrders();
            CoffeeShopDbInitializer.createRandomOrderItems();
            CoffeeShopDbInitializer.createPersonalPosition();
            CoffeeShopDbInitializer.createRandomPersonal();
            CoffeeShopDbInitializer.createPersonalSchedule();
            System.out.println("-".repeat(20));
        } catch (FileException e) {
            System.err.println(e.getMessage());
        }
    }
}
