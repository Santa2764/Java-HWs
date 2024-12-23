package com.company.tests;

import java.util.List;

public class FoodsFactory {
    public static List<String> getRandomFoodsTitle() {
        return TestUtils.getRandomFoodsTitle();
    }

    public static List<Integer> getRandomIntegers() {
        return TestUtils.getRandomIntegers();
    }
}
