package com.company;

import com.company.entity.Corporation;
import com.company.menu.CorporationMenu;
import com.company.tests.CorporationFactory;

public class Task5 {
    public static void main(String[] args) {
        // создаём корпорацию
        Corporation corporation = CorporationFactory.getCorporation();

        // получаем всех employees из репозитория (файла)
        corporation.loadFromRepository();

        // работа с меню улицы
        CorporationMenu corporationMenu = new CorporationMenu(corporation);
        corporationMenu.show();

        // взаимодействие с меню
        int numMenu;
        boolean isNotExit;
        do {
            numMenu = corporationMenu.getChoice();
            isNotExit = corporationMenu.execute(numMenu);
        } while(isNotExit);


        // всех employees сохраняем в репозиторий (файл)
        corporation.save();

        System.out.println("You are exit!");
    }
}
