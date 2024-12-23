package com.company;

import com.company.entity.Device;
import com.company.enums.Color;
import com.company.enums.DeviceType;
import com.company.tests.DeviceFactory;

import java.util.List;
import java.util.function.Predicate;

public class Task3 {
    public static void main(String[] args) {
        List<Device> devices = DeviceFactory.getDevices();

        System.out.println("Count: " + devices.size() + "\n");
        devices.forEach(System.out::println);

        Color color = Color.GRAY;
        System.out.println("\nFind by color <" + color + ">:");
        showFilteredDevices(devices, d -> d.getColor().equals(color));

        int year = 2015;
        System.out.println("\nFind by year <" + year + ">:");
        showFilteredDevices(devices, d -> d.getManufactureYear() == year);

        float price = 50000f;
        System.out.println("\nFind by price more <" + price + ">:");
        showFilteredDevices(devices, d -> d.getPrice() >= price);

        DeviceType deviceType = DeviceType.SMARTPHONE;
        System.out.println("\nFind by device type <" + deviceType + ">:");
        showFilteredDevices(devices, d -> d.getDeviceType().equals(deviceType));

        int firstYear = 2015;
        int lastYear = 2020;
        System.out.println("\nFind by range year <" + firstYear + " - " + lastYear + ">:");
        showFilteredDevices(devices, d -> d.getManufactureYear() >= firstYear && d.getManufactureYear() <= lastYear);
    }

    private static void showFilteredDevices(List<Device> devices, Predicate<Device> filterDevice) {
        devices.stream().filter(filterDevice).forEach(System.out::println);
    }
}
