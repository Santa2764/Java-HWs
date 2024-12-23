package com.example.CoffeeShop_Spring.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StartMenu {

    @Autowired
    private CoffeeShopMenu coffeeShopMenu;

    public void start() {
        coffeeShopMenu.show();

        int numMenu;
        boolean isNotExit;
        do {
            numMenu = coffeeShopMenu.getChoice();
            isNotExit = coffeeShopMenu.execute(numMenu);
        } while(isNotExit);

        System.out.println("Вы вышли из программы!");
    }
}
