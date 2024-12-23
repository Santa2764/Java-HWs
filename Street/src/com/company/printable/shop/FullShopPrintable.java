package com.company.printable.shop;

import com.company.entity.Shop;

public class FullShopPrintable implements IShopPrintable {
    @Override
    public void show(Shop shop) {
        String sep = "-".repeat(15);
        System.out.println(sep + " Shop " + sep);
        System.out.println("Address: " + shop.getAddress());
        System.out.println("Type shop: " + shop.getTypeShop());

        System.out.print("Departments: ");
        String[] departments = shop.getDepartments();
        for (int i = 0; i < departments.length; i++) {
            System.out.print(departments[i]);
            if (i != departments.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }
}
