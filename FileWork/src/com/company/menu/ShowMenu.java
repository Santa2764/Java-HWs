package com.company.menu;

public class ShowMenu {
    public static final int MIN_MENU_NUM = 1;

    private static final String[] menuTitles = new String[] {
            "Show all employees",
            "Show employee by age",
            "Show employee by surname",
            "Edit employee",
            "Delete employee",
            "Search by surname",
            "Save to file",
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
