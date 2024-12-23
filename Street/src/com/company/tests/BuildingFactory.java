package com.company.tests;

import com.company.entity.*;
import com.company.enums.AccreditalLevel;
import com.company.enums.TypeDepartment;
import com.company.enums.TypeShop;
import com.company.interfaces.IBuilding;
import com.company.printable.hospital.FullHospitalPrintable;
import com.company.printable.residentialhouse.FullResidentialPrintable;
import com.company.printable.school.FullSchoolPrintable;
import com.company.printable.shop.FullShopPrintable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

public class BuildingFactory {
    public static ResidentialHouse getResidentialHouse() {
        String address = TestUtils.getRandomUniqueAddress();
        int numberOfResidents = TestUtils.getRandomInteger(ResidentialHouse.MIN_NUMBER_RESIDENTS, ResidentialHouse.MAX_NUMBER_RESIDENTS);
        return new ResidentialHouse(address, numberOfResidents, new FullResidentialPrintable());
    }

    public static Shop getShop() {
        String address = TestUtils.getRandomUniqueAddress();

        TypeShop[] typeShops = TypeShop.values();
        TypeShop typeShop = typeShops[TestUtils.getRandomInteger(0, typeShops.length)];

        String[] departments = getRandomDepartmentsArr(typeShop.getDepartmentCount());

        Shop shop = new Shop(address, typeShop, new FullShopPrintable());
        shop.setDepartments(departments);

        return shop;
    }

    public static School getSchool() {
        String address = TestUtils.getRandomUniqueAddress();

        AccreditalLevel[] accreditalLevels = AccreditalLevel.values();
        AccreditalLevel accreditalLevel = accreditalLevels[TestUtils.getRandomInteger(0, accreditalLevels.length)];
        int numberOfStudents = TestUtils.getRandomInteger(accreditalLevel.getMinStudents(), accreditalLevel.getMaxStudents());

        School school = new School(address, accreditalLevel, new FullSchoolPrintable());
        school.setNumberOfStudents(numberOfStudents);

        return school;
    }

    public static Hospital getHospital() {
        String address = TestUtils.getRandomUniqueAddress();
        int numberOfBeds = TestUtils.getRandomInteger(1, 10) * 10;
        return new Hospital(address, numberOfBeds, new FullHospitalPrintable());
    }

    public static IBuilding getRandomBuilding() {
        int randInt = TestUtils.getRandomInteger(1, 10);
        if (randInt >= 1 && randInt <= 4) {
            return getResidentialHouse();
        }
        else if (randInt >= 5 && randInt <= 6) {
            return getShop();
        }
        else if (randInt >= 7 && randInt <= 8) {
            return getSchool();
        }
        else {
            return getHospital();
        }
    }

    public static String[] getRandomDepartmentsArr(int count) {
        List<TypeDepartment> randomDepartments = getRandomDepartments(count);
        return randomDepartments.stream()
                .map(TypeDepartment::name)
                .toArray(String[]::new);
    }

    private static List<TypeDepartment> getRandomDepartments(int count) {
        List<TypeDepartment> allDepartments = new ArrayList<>(EnumSet.allOf(TypeDepartment.class));
        Collections.shuffle(allDepartments);
        return allDepartments.subList(0, count);
    }
}
