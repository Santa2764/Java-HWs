package com.company.printable;

import com.company.entity.Corporation;
import com.company.interfaces.IPrintable;

public class BriefCorporationPrintable implements IPrintable {
    @Override
    public void print(Corporation corporation) {
        System.out.println("--------- Brief Corporation --------");
        System.out.println("Title: " + corporation.getTitle());
    }
}
