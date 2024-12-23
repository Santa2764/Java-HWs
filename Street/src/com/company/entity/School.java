package com.company.entity;

import com.company.enums.AccreditalLevel;
import com.company.printable.school.ISchoolPrintable;

public class School extends Building {
    private AccreditalLevel accreditalLevel;
    private int numberOfStudents;
    private ISchoolPrintable printableType;

    public School(String address, AccreditalLevel accreditalLevel, ISchoolPrintable printableType) {
        super(address);
        this.accreditalLevel = accreditalLevel;
        setPrintableType(printableType);
    }

    @Override
    public void show() {
        printableType.show(this);
    }

    @Override
    public void setFieldFromStr(String str) {
        System.out.println("Set fields from string for School!");
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public void setNumberOfStudents(int numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }

    public void setPrintableType(ISchoolPrintable printableType) {
        if (printableType != null) {
            this.printableType = printableType;
        }
    }
}
