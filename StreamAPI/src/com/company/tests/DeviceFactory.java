package com.company.tests;

import com.company.entity.Device;
import com.company.enums.Color;
import com.company.enums.DeviceType;

import java.util.List;
import java.util.stream.Stream;

public class DeviceFactory {
    public static Device getDevice() {
        String title = TestUtils.getRandomDeviceTitle();
        int manufactureYear = TestUtils.getRandomInteger(2010, TestUtils.CURR_YEAR);
        Color color = TestUtils.getRandomColor();
        DeviceType deviceType = TestUtils.getRandomDeviceType();
        float price = TestUtils.getRandomInteger(1000, 100000);

        return new Device(title, manufactureYear, price, color, deviceType);
    }

    public static List<Device> getDevices() {
        int limit = TestUtils.getRandomInteger(10, 15);
        return Stream.generate(DeviceFactory::getDevice).limit(limit).toList();
    }
}
