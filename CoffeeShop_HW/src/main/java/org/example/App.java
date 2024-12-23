package org.example;

import org.example.menu.StartMenu;
import org.example.service.CoffeeShopInitializer;

public class App {
    public static void main( String[] args ) {
        CoffeeShopInitializer initializer = new CoffeeShopInitializer();
        initializer.coffeeShopInitialize();

        StartMenu.start();
    }
}
