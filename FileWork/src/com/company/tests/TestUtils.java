package com.company.tests;

import java.util.Random;

public class TestUtils {
    private static final Random RAND = new Random();
    private static final int MIN_INT = 1;
    private static final int MAX_INT = 100_000_000;
    private static final String[] names = {
            "Alice", "Bob", "Charlie", "David", "Eva",
            "Frank", "Grace", "Hannah", "Isaac", "Jack",
            "Katherine", "Leo", "Mia", "Nina", "Oliver"
    };
    private static final String[] surnames = {
            "Smith", "Johnson", "Williams", "Brown", "Davis",
            "Miller", "Wilson", "Moore", "Taylor", "Anderson",
            "Thomas", "Jackson", "White", "Harris", "Martin"
    };
    private static final String[] titles = {
            "Tech Innovations Inc.", "Global Solutions Ltd.", "Creative Enterprises", "Future Dynamics", "Pinnacle Technologies",
            "NexGen Systems", "Visionary Labs", "Prime Ventures", "Elite Strategies", "Horizon Global",
            "Quantum Solutions", "Synergy Works", "Zenith Innovations", "Aurora Enterprises", "Vertex Industries"
    };

    public static String getRandomTitle() {
        return titles[RAND.nextInt(titles.length)];
    }

    public static String getRandomName() {
        return names[RAND.nextInt(names.length)];
    }

    public static String getRandomSurname() {
        return surnames[RAND.nextInt(surnames.length)];
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
