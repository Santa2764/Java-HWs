package com.company.tests;

import com.company.entity.Street;

import java.util.Random;
import java.util.Set;

public class TestUtils {
    private static final Random RAND = new Random();
    private static final int MIN_INT = 1;
    private static final int MAX_INT = 100_000_000;

    public static String getRandomUniqueAddress() {
        String numberHouse = String.valueOf(TestUtils.getRandomInteger(Street.MIN_NUMBER_ADDRESS, Street.MAX_NUMBER_ADDRESS));
        return generateUniqueHouseNumber(numberHouse, Street.GetHouseNumbers());
    }

    public static String generateUniqueHouseNumber(String numberHouse, Set<String> houseNumbers) {
        // если номера такого нет
        if (!houseNumbers.contains(numberHouse)) {
            return numberHouse;
        }

        char suffix = 'a';  // начальный символ
        String newNumber = numberHouse + suffix;

        while (houseNumbers.contains(newNumber)) {
            suffix++;
            newNumber = numberHouse + suffix;
        }

        return newNumber;
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
