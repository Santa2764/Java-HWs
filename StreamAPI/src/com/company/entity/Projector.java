package com.company.entity;

import com.company.enums.ProjectorManufacturer;

public class Projector {
    private final String title;
    private final int manufactureYear;
    private final ProjectorManufacturer manufacturer;
    private float price;

    public Projector(String title, int manufactureYear, ProjectorManufacturer manufacturer, float price) {
        this.title = title;
        this.manufactureYear = manufactureYear;
        this.manufacturer = manufacturer;
        this.price = price;
    }

    @Override
    public String toString() {
        return title + " - " + manufactureYear + " - " + manufacturer + " - " + price;
    }

    public String getTitle() {
        return title;
    }

    public int getManufactureYear() {
        return manufactureYear;
    }

    public ProjectorManufacturer getManufacturer() {
        return manufacturer;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
