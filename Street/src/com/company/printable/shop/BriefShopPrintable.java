package com.company.printable.shop;

import com.company.entity.Shop;

public class BriefShopPrintable implements IShopPrintable {
    @Override
    public void show(Shop shop) {
        String sep = "-".repeat(15);
        System.out.println(sep + " Shop " + sep);
        System.out.println("Address: " + shop.getAddress());
    }
}
