package com.company;

import com.company.tests.FoodsFactory;
import com.company.tests.TestUtils;

import java.util.*;
import java.util.function.Predicate;

public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> listFoods = FoodsFactory.getRandomFoodsTitle();

        showFoods(listFoods);
        System.out.println();

        showFoods(getFilteredFoods(listFoods, p -> p.length() < 5));
        System.out.println();

        System.out.print("Input food title to find: ");
        String userFoodTitle = scanner.nextLine();
        List<String> filteredFoods = getFilteredFoods(listFoods, p -> p.equalsIgnoreCase(userFoodTitle));
        System.out.println("Count users food: " + filteredFoods.size());

        System.out.print("Input first symbol to find: ");
        String userSymbol = scanner.nextLine();
        showFoods(getFilteredFoods(listFoods, p -> p.startsWith(userSymbol)));
        System.out.println();

        showFoods(getFilteredFoods(listFoods, p -> Arrays.stream(TestUtils.MILK_CATEGORY_FOODS).anyMatch(p::equalsIgnoreCase)));
    }

    private static List<String> getFilteredFoods(List<String> foods, Predicate<String> filterFoods) {
        return foods.stream().filter(filterFoods).toList();
    }

    private static void showFoods(List<String> foods) {
        foods.forEach(p -> System.out.print(p + " | "));
    }
}
