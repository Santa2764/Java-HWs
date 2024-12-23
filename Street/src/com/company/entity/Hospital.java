package com.company.entity;

import com.company.printable.hospital.IHospitalPrintable;

public class Hospital extends Building {
    public static final int MIN_NUMBER_BEDS = 1;
    public static final int MAX_NUMBER_BEDS = 100;

    private int numberOfBeds;
    private IHospitalPrintable printableType;

    public Hospital(String address, int numberOfBeds, IHospitalPrintable printableType) {
        super(address);
        this.numberOfBeds = numberOfBeds;
        setPrintableType(printableType);
    }

    @Override
    public void show() {
        printableType.show(this);
    }

    @Override
    public void setFieldFromStr(String str) {
        System.out.println("Set fields from string for Hospital!");
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public void setPrintableType(IHospitalPrintable printableType) {
        if (printableType != null) {
            this.printableType = printableType;
        }
    }
}
