package com.company.tests;

import com.company.enums.Color;
import com.company.enums.DeviceType;
import com.company.enums.ProjectorManufacturer;

import java.time.Year;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class TestUtils {
    public static final int CURR_YEAR = Year.now().getValue();
    public static final String[] MILK_CATEGORY_FOODS = { "Milk", "Kefir" };

    private static final Random RAND = new Random();
    private static final int MIN_INT = -150;
    private static final int MAX_INT = 150;
    private static final int MIN_GEN = 10;
    private static final int MAX_GEN = 16;
    private static final String[] FOODS_TITLES = {
            "Tea", "Banana", "Apple", "Corn", "Orange", "Water", "Milk", "Tomato", "Potato", "Cola", "Salt", "Sugar", "Bread", "Sprite", "Cherry", "Kefir"
    };
    private static final String[] deviceNames = {
            "Apple MacBook Pro",
            "Apple iPhone 14",
            "Apple iPad Pro",
            "Samsung Galaxy S23",
            "Google Pixel 8",
            "Dell XPS 13",
            "HP Spectre x360",
            "LG OLED C1 TV",
            "Sony Alpha A7 III",
            "Canon EOS R5",
            "Apple Watch Series 8",
            "Fitbit Sense",
            "PlayStation 5",
            "Xbox Series X",
            "Amazon Echo Dot"
    };

    public static ProjectorManufacturer getRandomProjectorManufacturer() {
        ProjectorManufacturer[] manufacturers = ProjectorManufacturer.values();
        return manufacturers[getRandomInteger(0, manufacturers.length - 1)];
    }

    public static String getRandomDeviceTitle() {
        return deviceNames[getRandomInteger(0, deviceNames.length - 1)];
    }

    public static Color getRandomColor() {
        Color[] colors = Color.values();
        return colors[getRandomInteger(0, colors.length - 1)];
    }

    public static DeviceType getRandomDeviceType() {
        DeviceType[] deviceTypes = DeviceType.values();
        return deviceTypes[getRandomInteger(0, deviceTypes.length - 1)];
    }


    public static List<String> getRandomFoodsTitle() {
        int sizeArr = getRandomInteger(MIN_GEN, MAX_GEN);
        return Stream.generate(TestUtils::getRandomFoodTitle).limit(sizeArr).toList();
    }

    public static String getRandomFoodTitle() {
        return FOODS_TITLES[getRandomInteger(0, FOODS_TITLES.length)];
    }


    public static List<Integer> getRandomIntegers() {
        int sizeArr = getRandomInteger(MIN_GEN, MAX_GEN);
        return Stream.generate(TestUtils::getRandomInteger).limit(sizeArr).toList();
    }

    public static int getRandomInteger() {
        return getRandomInteger(MIN_INT, MAX_INT);
    }

    public static int getRandomInteger(int max) {
        return getRandomInteger(MIN_INT, max);
    }

    public static int getRandomInteger(int min, int max) {
        return RAND.nextInt(min, max);
    }
}
