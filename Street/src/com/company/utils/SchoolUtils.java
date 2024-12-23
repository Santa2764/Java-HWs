package com.company.utils;

import com.company.enums.AccreditalLevel;

public class SchoolUtils {
    public static void displayAccreditalLevels() {
        int i = 1;
        for (AccreditalLevel level : AccreditalLevel.values()) {
            System.out.println((i++) + ") " + level.name() + ": " + level.getStudentRange() + " students");
        }
    }
}
