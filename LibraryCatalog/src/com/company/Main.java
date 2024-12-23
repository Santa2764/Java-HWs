package com.company;

import com.company.models.LibraryCatalog;
import com.company.menu.CatalogMenu;

public class Main {
    public static void main(String[] args) {
        // создаём каталог библиотеки
        LibraryCatalog catalog = new LibraryCatalog();
        catalog.initialize();

        // работа с меню каталога
        CatalogMenu catalogMenu = new CatalogMenu(catalog);
        catalogMenu.show();

        // взаимодействие с меню
        int numMenu;
        boolean isNotExit;
        do {
            numMenu = catalogMenu.getChoice();
            isNotExit = catalogMenu.execute(numMenu);
        } while(isNotExit);

        System.out.println("You are exit!");
    }
}