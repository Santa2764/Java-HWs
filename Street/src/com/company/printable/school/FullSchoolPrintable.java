package com.company.printable.school;

import com.company.entity.School;

public class FullSchoolPrintable implements ISchoolPrintable {
    @Override
    public void show(School school) {
        String sep = "-".repeat(15);
        System.out.println(sep + " School " + sep);
        System.out.println("Address: " + school.getAddress());
        System.out.println("Number of students: " + school.getNumberOfStudents());
    }
}
