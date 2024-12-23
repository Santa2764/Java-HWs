package com.company.entity;

import com.company.enums.Color;
import com.company.enums.DeviceType;

public class Device {
    private final String title;
    private final int manufactureYear;
    private final Color color;
    private final DeviceType deviceType;
    private float price;

    public Device(String title, int manufactureYear, float price, Color color, DeviceType deviceType) {
        this.title = title;
        this.manufactureYear = manufactureYear;
        this.price = price;
        this.color = color;
        this.deviceType = deviceType;
    }

    @Override
    public String toString() {
        return title + " - " + deviceType + " - " + color + " - " + manufactureYear + " - " + price;
    }

    public String getTitle() {
        return title;
    }

    public int getManufactureYear() {
        return manufactureYear;
    }

    public Color getColor() {
        return color;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
