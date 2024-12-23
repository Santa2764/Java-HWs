package com.company.tests;

import com.company.entity.Projector;
import com.company.enums.ProjectorManufacturer;

import java.util.List;
import java.util.stream.Stream;

public class ProjectorFactory {
    public static Projector getProjector() {
        String title = TestUtils.getRandomDeviceTitle();
        int manufactureYear = TestUtils.getRandomInteger(2010, TestUtils.CURR_YEAR);
        ProjectorManufacturer manufacturer = TestUtils.getRandomProjectorManufacturer();
        float price = TestUtils.getRandomInteger(1000, 100000);

        return new Projector(title, manufactureYear, manufacturer, price);
    }

    public static List<Projector> getProjectors() {
        int limit = TestUtils.getRandomInteger(10, 15);
        return Stream.generate(ProjectorFactory::getProjector).limit(limit).toList();
    }
}
