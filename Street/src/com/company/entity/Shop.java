package com.company.entity;

import com.company.enums.TypeShop;
import com.company.printable.shop.IShopPrintable;

import java.util.Arrays;

public class Shop extends Building {
    private TypeShop typeShop;
    private String[] departments;
    private IShopPrintable printableType;

    public Shop(String address, TypeShop typeShop, IShopPrintable printableType) {
        super(address);
        this.typeShop = typeShop;
        setPrintableType(printableType);
    }

    @Override
    public void show() {
        printableType.show(this);
    }

    @Override
    public void setFieldFromStr(String str) {
        System.out.println("Set fields from string for Shop!");
    }

    public TypeShop getTypeShop() {
        return typeShop;
    }

    public String[] getDepartments() {
        return Arrays.copyOf(departments, departments.length);
    }

    public void setDepartments(String[] departments) {
        this.departments = Arrays.copyOf(departments, departments.length);
    }

    public void setPrintableType(IShopPrintable printableType) {
        if (printableType != null) {
            this.printableType = printableType;
        }
    }
}
