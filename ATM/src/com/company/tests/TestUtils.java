package com.company.tests;

import java.util.Random;

public class TestUtils {
    private static final Random RAND = new Random();
    private static final int MIN_INT = 1;
    private static final int MAX_INT = 100_000_000;
    private static final int[] DENOMINATIONS = { 1, 2, 5, 10, 20, 50, 100, 200, 500 };

    public static int[] getRandomDenominations() {
        int count = getRandomInteger(3, DENOMINATIONS.length);
        int[] arr = new int[count];
        for (int i = 0; i < count; i++) {
            arr[i] = DENOMINATIONS[DENOMINATIONS.length - count + i];
        }
        return arr;
    }

    public static int getRandomInteger() {
        return getRandomInteger(MIN_INT, MAX_INT);
    }

    public static int getRandomInteger(int min) {
        return getRandomInteger(min, MAX_INT);
    }

    public static int getRandomInteger(int min, int max) {
        return RAND.nextInt(min, max);
    }
}
