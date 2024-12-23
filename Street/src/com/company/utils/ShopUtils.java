package com.company.utils;

import com.company.enums.TypeShop;

public class ShopUtils {
    public static void displayTypeShops() {
        int i = 1;
        for (TypeShop type : TypeShop.values()) {
            System.out.println((i++) + ") " + type.name() + ": " + type.getDepartmentCount() + " departments");
        }
    }
}
