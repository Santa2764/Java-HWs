package org.example.utils;

import org.example.entity.Product;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ListWorkTest {
    private static final List<Product> PRODUCTS = new ArrayList<>();
    private static final List<Product> PRODUCTS_ATB = new ArrayList<>();
    private static final List<Product> PRODUCTS_MERGED_ATB = new ArrayList<>();
    private static final List<Product> EMPTY_PRODUCTS = new ArrayList<>();
    private static final List<String> VALID_CSV = new ArrayList<>();
    private static final List<String> EMPTY_CSV = new ArrayList<>();

    @BeforeAll
    public static void initialize() {
        PRODUCTS.add(new Product("Сильпо", "Гречка", 30.25, 120));
        PRODUCTS.add(new Product("АТБ", "Сахар", 21.25, 170));
        PRODUCTS.add(new Product("Сильпо", "Мука", 26.50, 80));
        PRODUCTS.add(new Product("АТБ", "Молоко", 21.40, 110));
        PRODUCTS.add(new Product("АТБ", "Хлеб", 15.00, 50));
        PRODUCTS.add(new Product("АТБ", "Яйца", 35.60, 200));
        PRODUCTS.add(new Product("Сильпо", "Масло", 45.20, 90));
        PRODUCTS.add(new Product("АТБ", "Сыр", 120.00, 30));
        PRODUCTS.add(new Product("Сильпо", "Творог", 40.80, 70));
        PRODUCTS.add(new Product("АТБ", "Колбаса", 150.25, 40));
        PRODUCTS.add(new Product("АТБ", "Шоколад", 50.50, 100));
        PRODUCTS.add(new Product("АТБ", "Вода", 10.00, 300));
        PRODUCTS.add(new Product("Сильпо", "Сок", 22.35, 150));

        PRODUCTS_ATB.add(new Product("АТБ", "Вода", 10.00, 300));
        PRODUCTS_ATB.add(new Product("АТБ", "Масло", 150.25, 40));
        PRODUCTS_ATB.add(new Product("АТБ", "Вода", 21.40, 110));
        PRODUCTS_ATB.add(new Product("АТБ", "Хлеб", 15.00, 50));
        PRODUCTS_ATB.add(new Product("АТБ", "Масло", 35.60, 200));
        PRODUCTS_ATB.add(new Product("АТБ", "Гоша", 35.60, 200));

        PRODUCTS_MERGED_ATB.add(new Product("АТБ", "Вода", 13.06, 410));
        PRODUCTS_MERGED_ATB.add(new Product("АТБ", "Гоша", 35.60, 200));
        PRODUCTS_MERGED_ATB.add(new Product("АТБ", "Масло", 54.71, 240));
        PRODUCTS_MERGED_ATB.add(new Product("АТБ", "Хлеб", 15.00, 50));

        VALID_CSV.add("Сильпо;Гречка;30.25;120");
        VALID_CSV.add("АТБ;Сахар;21.25;170;");
        VALID_CSV.add("Сильпо;Мука;24.2;99;");
        VALID_CSV.add("АТБ;Мука;23.5;80;");
        VALID_CSV.add("АТБ;Гречка;31.25;120;");
    }

    @Test
    public void getFilteredList_ShouldReturnFilteredList_WhenFilteredByAtb() {
        List<Product> atbProducts = ListWork.getFilteredList(PRODUCTS, p -> p.getTitleShop().equalsIgnoreCase("атб"));
        assertEquals(8, atbProducts.size(), "Must be 8 products from ATB");
        assertEquals("Сахар", atbProducts.get(0).getName(), "First product must be 'Sugar'");
        assertEquals("Вода", atbProducts.get(atbProducts.size() - 1).getName(), "Last product must be 'Water'");
    }

    @Test
    public void getFilteredList_ShouldReturnEmptyList_WhenListEmptyOrNull() {
        List<Product> atbProducts = ListWork.getFilteredList(EMPTY_PRODUCTS, p -> p.getTitleShop().equalsIgnoreCase("атб"));
        assertTrue(atbProducts.isEmpty(), "Products must be empty");

        List<Product> atbProductsNull = ListWork.getFilteredList(null, p -> p.getTitleShop().equalsIgnoreCase("атб"));
        assertTrue(atbProductsNull.isEmpty(), "Products (with null) must be empty");
    }

    @Test
    public void changeListWithSkip_ShouldReturnThrownNullPointerException_WhenListNull() {
        assertThrows(NullPointerException.class, () -> ListWork.changeListWithSkip(null, 0),
                "Expected NullPointerException when list is null, but it wasn't thrown.");
    }

    @Test
    public void changeListWithSkip_ShouldReturnThrownIllegalArgumentException_WhenListEmpty() {
        assertThrows(IllegalArgumentException.class, () -> ListWork.changeListWithSkip(PRODUCTS, -5),
                "Expected IllegalArgumentException when skip value is negative, but it wasn't thrown.");
        assertThrows(IllegalArgumentException.class, () -> ListWork.changeListWithSkip(PRODUCTS, 1000),
                "Expected IllegalArgumentException when skip value exceeds list size, but it wasn't thrown.");
    }

    @Test
    public void changeListWithSkip_ShouldReturnSkipList_WhenListIsNotNull() {
        int skip = 4;
        int expected = 9;
        List<Product> products = ListWork.changeListWithSkip(PRODUCTS, skip);
        assertEquals(expected, products.size(), "Must be " + expected + " products and " + skip + " skipped");
    }

    @Test
    public void getListProductsByListCsv_ShouldReturnProductList_WhenCsvValid() {
        List<Product> products = ListWork.getListProductsByListCsv(VALID_CSV);
        assertNotNull(products, "Product list should not be null");
        assertEquals(VALID_CSV.size(), products.size(), "Product list should contain " + VALID_CSV.size() + " products");

        Product firstProduct = products.get(0);
        assertEquals("Сильпо", firstProduct.getTitleShop(), "First product's shop should be 'Сильпо'");
        assertEquals("Гречка", firstProduct.getName(), "First product's name should be 'Гречка'");
        assertEquals(30.25, firstProduct.getPrice(), 0.01, "First product's price should be 30.25");
        assertEquals(120, firstProduct.getQuantity(), "First product's quantity should be 120");

        Product secondProduct = products.get(products.size() - 1);
        assertEquals("АТБ", secondProduct.getTitleShop(), "Second product's shop should be 'АТБ'");
        assertEquals("Гречка", secondProduct.getName(), "Second product's name should be 'Гречка'");
        assertEquals(31.25, secondProduct.getPrice(), 0.01, "Second product's price should be 31.25");
        assertEquals(120, secondProduct.getQuantity(), "Second product's quantity should be 120");
    }

    @Test
    public void getListProductsByListCsv_ShouldThrowIllegalArgumentException_WhenCsvNull() {
        assertThrows(IllegalArgumentException.class, () -> ListWork.getListProductsByListCsv(null),
                "Expected IllegalArgumentException when CSV list is null, but it wasn't thrown.");
    }

    @Test
    public void getListProductsByListCsv_ShouldThrowIllegalArgumentException_WhenCsvEmpty() {
        assertThrows(IllegalArgumentException.class, () -> ListWork.getListProductsByListCsv(EMPTY_CSV),
                "Expected IllegalArgumentException when CSV list is empty, but it wasn't thrown.");
    }

    @Test
    public void mergeIdenticalProductsWithSort_ShouldThrowIllegalArgumentException_WhenListEmptyOrNull() {
        assertThrows(IllegalArgumentException.class,
                () -> ListWork.mergeIdenticalProductsWithSort(null),
                "Expected IllegalArgumentException when input list is null");

        assertThrows(IllegalArgumentException.class,
                () -> ListWork.mergeIdenticalProductsWithSort(EMPTY_PRODUCTS),
                "Expected IllegalArgumentException when input list is empty");
    }

    @Test
    public void mergeIdenticalProductsWithSort_ShouldMergedList_WhenListNotNUllOrEmpty() {
        List<Product> products = ListWork.mergeIdenticalProductsWithSort(PRODUCTS_ATB);
        assertTrue(products.size() == 4, "There should be 3 unique products after merging");

        for (int i = 0; i < PRODUCTS_MERGED_ATB.size(); i++) {
            Product expectedProduct = PRODUCTS_MERGED_ATB.get(i);
            Product actualProduct = products.get(i);
            assertEquals(
                    expectedProduct.getName(), actualProduct.getName(),
                    "Product names must match (" + expectedProduct.getName() + ")"
            );
            assertEquals(
                    expectedProduct.getQuantity(), actualProduct.getQuantity(),
                    "Product quantities must match (" + expectedProduct.getQuantity() + ")"
            );
            assertEquals(
                    expectedProduct.getPrice(), actualProduct.getPrice(),
                    "Product prices must match (" + expectedProduct.getPrice() + ")"
            );
        }
    }
}
