package org.example.menu;

public class StartMenu {
    public static void start() {
        CoffeeShopMenu coffeeShopMenu = new CoffeeShopMenu();
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
