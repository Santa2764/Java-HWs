package org.example.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductTest {
    private static final String VALID_CSV = "Cola;35.5;1000;";
    private static final String VALID_CSV_2 = "ATB;Cola;35.5;1000;";
    private static final String INVALID_CSV = "Cola;1000;";
    private static final String INVALID_CSV_2 = "Cola;35.5;1000";
    private static Product PRODUCT;

    @BeforeEach
    public void setUp() {
        PRODUCT = new Product("ATB", "Cola", 35.5f, 1000);
    }

    @Test
    public void convertToCsv_ShouldReturnStrCsv() {
        assertEquals(VALID_CSV, PRODUCT.convertToCsv(), "");
    }

    @Test
    public void clone_ShouldReturnCopyOfProduct() {
        Product actual = PRODUCT.clone();

        assertNotSame(PRODUCT, actual, "Clone should be a different object");

        assertEquals(PRODUCT.getTitleShop(), actual.getTitleShop(), "Shop names should match");
        assertEquals(PRODUCT.getName(), actual.getName(), "Product names should match");
        assertEquals(PRODUCT.getPrice(), actual.getPrice(), 0.01, "Prices should match");
        assertEquals(PRODUCT.getQuantity(), actual.getQuantity(), "Quantities should match");

        actual.setQuantity(123);
        assertNotEquals(PRODUCT.getQuantity(), actual.getQuantity(), "Quantity shouldn't match");
    }

    @Test
    public void convertCsvToProduct_ShouldReturnThrownIllegalArgumentException_WhenStrCsvIsNullOrEmpty() {
        assertThrows(IllegalArgumentException.class,
                () -> PRODUCT.convertCsvToProduct(null),
                "Expected IllegalArgumentException when input CSV is null"
        );
        assertThrows(IllegalArgumentException.class,
                () -> PRODUCT.convertCsvToProduct(""),
                "Expected IllegalArgumentException when input CSV is empty"
        );
    }

    @Test
    public void convertCsvToProduct_ShouldThrowIllegalArgumentException_WhenStrCsvIsInvalidFormat() {
        assertThrows(IllegalArgumentException.class, () ->
                PRODUCT.convertCsvToProduct(INVALID_CSV),
                "Expected IllegalArgumentException when CSV has invalid format"
        );

        assertThrows(IllegalArgumentException.class, () ->
                PRODUCT.convertCsvToProduct(INVALID_CSV_2),
                "Expected IllegalArgumentException when CSV has another invalid format"
        );
    }

    @Test
    public void convertCsvToProduct_ShouldReturnProduct_WhenStrCsvIsValid() {
        Product copyProduct = PRODUCT.clone();
        PRODUCT.convertCsvToProduct(VALID_CSV_2);

        assertEquals(copyProduct.getTitleShop(), PRODUCT.getTitleShop(), "Shop names should match");
        assertEquals(copyProduct.getName(), PRODUCT.getName(), "Product names should match");
        assertEquals(copyProduct.getPrice(), PRODUCT.getPrice(), 0.01, "Prices should match");
        assertEquals(copyProduct.getQuantity(), PRODUCT.getQuantity(), 0.01, "Quantity should match");
    }
}
