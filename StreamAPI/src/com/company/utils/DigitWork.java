package com.company.utils;

public class DigitWork {
    public static boolean isMirrorNumber(int num) {
        String strNum = String.valueOf(num);
        StringBuilder strBuilder = new StringBuilder(strNum);
        return strBuilder.reverse().toString().equals(strNum);
    }
}
