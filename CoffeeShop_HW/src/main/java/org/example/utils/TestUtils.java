package org.example.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Year;
import java.util.Random;

public class TestUtils {
    private static final Random RAND = new Random();
    private static final int MIN_INT = 1;
    private static final int MAX_INT = 100_000_000;
    private static final int SIZE_NUM_TEL = 10;

    public static final int CURRENT_YEAR = Year.now().getValue();

    public static LocalDate getRandomDate() {
        long minDay = LocalDate.of(1975, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(CURRENT_YEAR, 12, 31).toEpochDay();
        long randDay = RAND.nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randDay);
    }

    public static LocalTime getRandomTime() {
        int hour = RAND.nextInt(6, 22);
        int minute = 0;
        int second = 0;
        return LocalTime.of(hour, minute, second);
    }

    public static String getRandomNumTel() {
        StringBuilder sb = new StringBuilder("+");
        for (int i = 0; i < SIZE_NUM_TEL; i++) {
            sb.append(getRandomInteger(1, 9));
        }
        return sb.toString();
    }

    public static String getEmailByName(String name) {
        return name + "@gmail.com";
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

    public static double getRandomDouble(double min, double max) {
        return RAND.nextDouble(min, max);
    }
}
