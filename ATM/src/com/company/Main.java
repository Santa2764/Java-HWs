package com.company;

import com.company.entity.ATM;
import com.company.entity.Bank;
import com.company.menu.AtmMenu;
import com.company.tests.TaskFactory;

public class Main {
    public static void main(String[] args) throws Exception {
        // создаём банк и банкоматы
        ATM[] atms = TaskFactory.getATMs();
        Bank bank = new Bank(atms);

        // работа с меню банкомата
        AtmMenu atmMenu = new AtmMenu(bank);
        atmMenu.initializeAtms();  // полная инициализация банкоматов
        atmMenu.show();

        // взаимодействие с меню
        int numMenu;
        boolean isNotExit;
        do {
            numMenu = atmMenu.getChoice();
            isNotExit = atmMenu.execute(numMenu);
        } while(isNotExit);

        System.out.println("You are exit!");
    }
}