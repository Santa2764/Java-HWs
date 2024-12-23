package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Cloneable {
    private String titleShop;
    private String name;
    private double price;
    private int quantity;

    @Override
    public Product clone() {
        try {
            return (Product) super.clone();
        }
        catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public String convertToCsv() {
        return name + ";" + price + ";" + quantity + ";";
    }

    public void convertCsvToProduct(final String strCsv) {
        if (strCsv == null || strCsv.isEmpty()) {
            throw new IllegalArgumentException("The csv string cannot be null or empty");
        }

        String[] listProdProps = strCsv.split(";");
        if (listProdProps.length != 4) {
            throw new IllegalArgumentException("The csv string must contain exactly four elements");
        }

        titleShop = listProdProps[0];
        name = listProdProps[1];
        price = Double.parseDouble(listProdProps[2]);
        quantity = Integer.parseInt(listProdProps[3]);
    }
}
