package com.company.printable.residentialhouse;

import com.company.entity.ResidentialHouse;

public class BriefResidentialPrintable implements IResidentialPrintable {
    @Override
    public void show(ResidentialHouse residentialHouse) {
        String sep = "-".repeat(15);
        System.out.println(sep + " Residential house " + sep);
        System.out.println("Address: " + residentialHouse.getAddress());
    }
}