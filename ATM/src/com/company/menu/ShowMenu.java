package com.company.menu;

public class ShowMenu {
    public static final int MIN_MENU_NUM = 1;

    private static final String[] menuTitles = new String[] {
            "Get total cash",
            "Initialize atms",
            "Load cash",
            "Withdrawal cash",
            "Get amount atms",
            "Get info atm",
            "Get all info atms",
            "Exit"
    };

    public static void show() {
        for (int i = 0; i < menuTitles.length; i++) {
            System.out.println("\t" + (i + 1) + ". " + menuTitles[i]);
        }
    }

    public static int getSizeMenu() {
        return menuTitles.length;
    }
}
