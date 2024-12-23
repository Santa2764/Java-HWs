package com.company.menu;

public class ShowMenu {
    public static final int MIN_MENU_NUM = 1;

    private static final String[] menuTitles = new String[] {
            "Show all building",
            "Get count building",
            "Add residential house",
            "Add shop",
            "Add school",
            "Add hospital",
            "Remove building",
            "Find shops around residential house",
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
