package com.company;

import com.company.tests.FoodsFactory;
import com.company.utils.DigitWork;

import java.util.List;

public class Task1 {
    public static void main(String[] args) {
        List<Integer> listInt = FoodsFactory.getRandomIntegers();

        listInt.forEach(n -> System.out.print(n + " | "));
        System.out.println("\nCount: " + listInt.size());

        long countPositive = listInt.stream()
                .filter(n -> n > 0)
                .count();
        System.out.println("Count positive: " + countPositive);

        long countNegative = listInt.stream()
                .filter(n -> n < 0)
                .count();
        System.out.println("Count negative: " + countNegative);

        long countTwoDigit = listInt.stream()
                .filter(n -> (n >= 10 && n <= 99) || (n >= -99 && n <= -10))
                .count();
        System.out.println("Count two digit: " + countTwoDigit);

        long countMirrorNum = listInt.stream()
                .filter(DigitWork::isMirrorNumber)
                .count();
        System.out.println("Count mirror number: " + countMirrorNum);
    }
}
