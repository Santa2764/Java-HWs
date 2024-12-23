package com.company.utils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ListWork {
    public static <T> List<T> getUniquesByAnotherList(final List<T> list1, final List<T> list2) {
        return list1.stream().filter(s -> !list2.contains(s)).toList();
    }

    public static <T> String listToString(List<T> list, String sep) {
        return list.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(sep));
    }

    public static <T> T getElement(final List<T> list, Predicate<T> predicate) {
        return list.stream().filter(predicate).findFirst().orElse(null);
    }

    public static <T> List<T> getFilteredList(final List<T> list, final Predicate<T> filtered) {
        return list.stream().filter(filtered).toList();
    }

    public static <T> T getFindMaxElem(final List<T> list, final Comparator<T> comparator) {
        return list.stream()
                .max(comparator)
                .orElseThrow(() -> new IllegalArgumentException("List is empty"));
    }

    public static <T> T getFindMaxElemNestedArr(final List<List<T>> list, final Comparator<T> comparator) {
        return list.stream()
                .flatMap(List::stream)
                .max(comparator)
                .orElseThrow(() -> new IllegalArgumentException("List is empty"));
    }

    public static List<Integer> getListIntByStrNum(String strNum) {
        return Arrays.stream(strNum.split(" "))
                .map(Integer::parseInt)
                .toList();
    }

    public static int getSumNestedList(final List<List<Integer>> list) {
        return list.stream()
                .flatMap(List::stream)
                .reduce(0, Integer::sum);
    }
}
