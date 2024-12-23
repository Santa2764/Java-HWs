package com.company.tests;

import com.company.entity.Street;
import com.company.interfaces.IBuilding;

import java.util.TreeSet;

public class StreetFactory {
    public static Street getStreet() {
        TreeSet<IBuilding> buildings = new TreeSet<>();
        int count = TestUtils.getRandomInteger(10, 20);

        for (int i = 0; i < count; i++) {
            IBuilding building = BuildingFactory.getRandomBuilding();
            buildings.add(building);
            Street.AddHouseNumber(building.getAddress());
        }

        return new Street("MyStreet", buildings);
    }
}
