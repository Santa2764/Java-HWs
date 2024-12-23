package com.company.tests;

import java.time.LocalDate;
import java.time.Year;
import java.util.Random;

public class TestUtils {
    private static final Random RAND = new Random();
    private static final int MIN_INT = 1;
    private static final int MAX_INT = 100_000_000;
    private static final int CURRENT_YEAR = Year.now().getValue();

    private static final String[] FIRST_NAMES = {
            "Daniil", "Bob", "Thomas", "Robert", "Andrey", "Max", "Tom", "Anna", "Ksenia", "Daniel"
    };
    private static final String[] LAST_NAMES = {
            "Sobolev", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis", "Rodriguez", "Martinez"
    };
    private static final String[] ANY_TITLES = {
            "To Kill a Mockingbird",
            "1984",
            "Pride and Prejudice",
            "The Great Gatsby",
            "One Hundred Years of Solitude",
            "Moby-Dick",
            "War and Peace",
            "Brave New World",
            "The Catcher in the Rye",
            "The Lord of the Rings"
    };
    private static final String[] BOOK_GENRES = {
            "Science Fiction",
            "Fantasy",
            "Mystery",
            "Thriller",
            "Romance",
            "Historical Fiction",
            "Non-Fiction",
            "Biography",
            "Horror",
            "Young Adult"
    };


    public static String getRandomFirstName() {
        return FIRST_NAMES[RAND.nextInt(FIRST_NAMES.length)];
    }

    public static String getRandomLastName() {
        return LAST_NAMES[RAND.nextInt(LAST_NAMES.length)];
    }

    public static String getRandomBookGenre() {
        return BOOK_GENRES[RAND.nextInt(BOOK_GENRES.length)];
    }

    public static String getRandomAnyTitle() {
        return ANY_TITLES[RAND.nextInt(ANY_TITLES.length)];
    }

    public static String[] getRandomAnyTitles() {
        int size = RAND.nextInt(2, 10);
        String[] titles = new String[size];
        for (int i = 0; i < size; i++) {
            titles[i] = ANY_TITLES[RAND.nextInt(ANY_TITLES.length)];
        }
        return titles;
    }

    public static LocalDate getRandomDate() {
        long minDay = LocalDate.of(1900, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(CURRENT_YEAR - 10, 12, 31).toEpochDay();
        long randDay = RAND.nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randDay);
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
