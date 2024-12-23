package com.company.entity;

import com.company.printable.residentialhouse.IResidentialPrintable;

public class ResidentialHouse extends Building {
    public static final int MIN_NUMBER_RESIDENTS = 10;
    public static final int MAX_NUMBER_RESIDENTS = 100;

    private int numberOfResidents;
    private IResidentialPrintable printableType;

    public ResidentialHouse(String address, int numberOfResidents, IResidentialPrintable printableType) {
        super(address);
        this.numberOfResidents = numberOfResidents;
        setPrintableType(printableType);
    }

    @Override
    public void show() {
        printableType.show(this);
    }

    @Override
    public void setFieldFromStr(String str) {
        System.out.println("Set fields from string for ResidentialHouse!");
    }

    public int getNumberOfResidents() {
        return numberOfResidents;
    }

    public void setNumberOfResidents(int numberOfResidents) {
        this.numberOfResidents = numberOfResidents;
    }

    public void setPrintableType(IResidentialPrintable printableType) {
        if (printableType != null) {
            this.printableType = printableType;
        }
    }
}
