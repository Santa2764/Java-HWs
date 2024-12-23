package com.company.wrappers;

import java.lang.Integer;

public class WrapperTest {
    public static void main(String[] args) {
        // ----------- 2 -----------
        // int
        Integer i1 = 10;
        Integer i2 = Integer.valueOf(10);
        //Integer i3 = new Integer(10);
        Integer i4 = Integer.parseInt("10");

        // float
        Float f1 = 25.5f;
        Float f2 = Float.valueOf(25.5f);
        //Float f3 = new Float(25.5f);
        Float f4 = Float.parseFloat("25.5f");

        // boolean
        Boolean b1 = true;
        Boolean b2 = Boolean.valueOf(true);
        //Boolean b3 = new Boolean(true);
        Boolean b4 = Boolean.parseBoolean("true");

        // char
        Character ch = 'd';
        Character ch2 = Character.valueOf('d');
        //Character ch3 = new Character(20);

        // ----------- 3 -----------
        Double d1 = 12.3;
        byte byteValue = d1.byteValue();
        short shortValue = d1.shortValue();
        int intValue = d1.intValue();
        float floatValue = d1.floatValue();
        long longValue = d1.longValue();

        // ----------- 4 -----------
        Double d2 = 0.0;
        Double nanValue = d1 / d2;
        Double infinityValue = d2 / d2;

        String nanStr = (nanValue.isNaN()) ? "NaN" : "Infinity";
        System.out.println("Переменная nanValue = " + nanStr);

        String infinityStr = (infinityValue.isNaN()) ? "NaN" : "Infinity";
        System.out.println("Переменная infinityValue = " + infinityStr);

        // ----------- 5 -----------
        Long l1 = 120L;
        Long l2 = 120L;
        System.out.println(l1 == l2);
        l1 = 1200L;
        l2 = 1200L;
        System.out.println(l1 == l2);
    }
}
