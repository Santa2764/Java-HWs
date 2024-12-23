package org.example.utils;

import org.example.entity.Product;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ListWork {
    public static <T> List<T> getFilteredList(final List<T> list, final Predicate<T> filtered) {
        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }
        return list.stream().filter(filtered).toList();
    }

    public static <T> List<T> changeListWithSkip(final List<T> list, final int skip) {
        if (list == null) {
            throw new NullPointerException("list is null");
        }
        if (skip < 0 || skip > list.size()) {
            throw new IllegalArgumentException(list.size() + " is out of range");
        }
        return list.stream().skip(skip).toList();
    }

    public static List<Product> getListProductsByListCsv(final List<String> stringsCsv) {
        if (stringsCsv == null || stringsCsv.isEmpty()) {
            throw new IllegalArgumentException("List of products cannot be null or empty");
        }
        return stringsCsv.stream()
                .map(stringCsv -> {
                    Product newProd = new Product();
                    newProd.convertCsvToProduct(stringCsv);
                    return newProd;
                })
                .collect(Collectors.toList());
    }

    public static List<Product> mergeIdenticalProductsWithSort(final List<Product> products) {
        if (products == null || products.isEmpty()) {
            throw new IllegalArgumentException("List of products cannot be null or empty");
        }
        Map<String, Product> mergedProducts = products.stream()
                .collect(Collectors.toMap(
                        Product::getName,
                        Product::clone,
                        (p1, p2) -> {
                            int totalQuantity = p1.getQuantity() + p2.getQuantity();
                            double averagePrice = (p1.getPrice() * p1.getQuantity() + p2.getPrice() * p2.getQuantity()) / totalQuantity;
                            averagePrice = Math.round(averagePrice * 100.0) / 100.0;

                            p1.setPrice(averagePrice);
                            p1.setQuantity(totalQuantity);
                            return p1;
                        }
                ));
        return mergedProducts.values().stream()
                .sorted(Comparator.comparing(Product::getName))
                .toList();
    }
}
