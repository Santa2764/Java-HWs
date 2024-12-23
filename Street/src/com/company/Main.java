package com.company;

import com.company.entity.Street;
import com.company.menu.StreetMenu;
import com.company.tests.StreetFactory;

public class Main {
    public static void main(String[] args) {
        // создаём улицу
        Street street = StreetFactory.getStreet();

        // работа с меню улицы
        StreetMenu streetMenu = new StreetMenu(street);
        streetMenu.show();

        // взаимодействие с меню
        int numMenu;
        boolean isNotExit;
        do {
            numMenu = streetMenu.getChoice();
            isNotExit = streetMenu.execute(numMenu);
        } while(isNotExit);

        System.out.println("You are exit!");
    }
}