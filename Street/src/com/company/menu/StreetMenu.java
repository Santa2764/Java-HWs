package com.company.menu;

import com.company.entity.*;
import com.company.enums.AccreditalLevel;
import com.company.enums.TypeShop;
import com.company.interfaces.IBuilding;
import com.company.printable.hospital.FullHospitalPrintable;
import com.company.printable.residentialhouse.FullResidentialPrintable;
import com.company.printable.school.FullSchoolPrintable;
import com.company.printable.shop.FullShopPrintable;
import com.company.tests.BuildingFactory;
import com.company.tests.TestUtils;
import com.company.utils.Input;
import com.company.utils.SchoolUtils;
import com.company.utils.ShopUtils;

import java.util.List;

public class StreetMenu {
    private final Street street;

    public StreetMenu(Street street) {
        this.street = street;
    }


    public void show() {
        System.out.println("Street menu:");
        ShowMenu.show();
    }

    public int getChoice() {
        int maxChoice = ShowMenu.getSizeMenu();
        return Input.inputInteger(
                "\nPlease enter the number menu: ",
                "Invalid choice, need from " + ShowMenu.MIN_MENU_NUM + " to " + maxChoice,
                ShowMenu.MIN_MENU_NUM,
                maxChoice
        );
    }

    public boolean execute(int choiceMenu) {
        switch (choiceMenu) {
            case 1:
                showAll();
                break;

            case 2:
                getCountBuilding();
                break;

            case 3:
                addResidentialHouse();
                break;

            case 4:
                addShop();
                break;

            case 5:
                addSchool();
                break;

            case 6:
                addHospital();
                break;

            case 7:
                removeBuildings();
                break;

            case 8:
                findShops();
                break;

            default:
                return false;
        }
        return true;
    }


    public void showAll() {
        street.showInfo();
    }

    public void getCountBuilding() {
        System.out.println("Count buildings: " + street.getCountBuildings());
    }

    public void addResidentialHouse() {
        String numAddressStr = inputNumAddress();

        int numberOfResidents = Input.inputInteger(
                "Please enter the number of residents: ",
                "Invalid number of residents, need from " + ResidentialHouse.MIN_NUMBER_RESIDENTS + " to " + ResidentialHouse.MAX_NUMBER_RESIDENTS,
                ResidentialHouse.MIN_NUMBER_RESIDENTS,
                ResidentialHouse.MAX_NUMBER_RESIDENTS
        );

        try {
            ResidentialHouse residentialHouse = new ResidentialHouse(numAddressStr, numberOfResidents, new FullResidentialPrintable());
            street.addBuilding(residentialHouse, numAddressStr);
            System.out.println("Successfully added!");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addShop() {
        String numAddressStr = inputNumAddress();

        ShopUtils.displayTypeShops();
        int maxValue = TypeShop.values().length;
        int typeShopNum = Input.inputInteger(
                "Please enter the number type shop: ",
                "Invalid number type shop, need from 1 to " + maxValue,
                1,
                maxValue
        );
        TypeShop typeShop = TypeShop.values()[typeShopNum - 1];
        String[] departments = BuildingFactory.getRandomDepartmentsArr(typeShop.getDepartmentCount());

        try {
            Shop shop = new Shop(numAddressStr, typeShop, new FullShopPrintable());
            shop.setDepartments(departments);
            street.addBuilding(shop, numAddressStr);
            System.out.println("Successfully added!");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addSchool() {
        String numAddressStr = inputNumAddress();

        SchoolUtils.displayAccreditalLevels();
        int maxValue = AccreditalLevel.values().length;
        int levelNum = Input.inputInteger(
                "Please enter the number accredital level: ",
                "Invalid number accredital level, need from 1 to " + maxValue,
                1,
                maxValue
        );
        AccreditalLevel accreditalLevel = AccreditalLevel.values()[levelNum - 1];
        int numberOfStudents = TestUtils.getRandomInteger(accreditalLevel.getMinStudents(), accreditalLevel.getMaxStudents());

        try {
            School school = new School(numAddressStr, accreditalLevel, new FullSchoolPrintable());
            school.setNumberOfStudents(numberOfStudents);
            street.addBuilding(school, numAddressStr);
            System.out.println("Successfully added!");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addHospital() {
        String numAddressStr = inputNumAddress();
        int numberOfBeds = Input.inputInteger(
                "Please enter the number of beds: ",
                "Invalid number of beds, need from " + Hospital.MIN_NUMBER_BEDS + " to " + Hospital.MAX_NUMBER_BEDS,
                Hospital.MIN_NUMBER_BEDS,
                Hospital.MAX_NUMBER_BEDS
        );
        try {
            Hospital hospital = new Hospital(numAddressStr, numberOfBeds, new FullHospitalPrintable());
            street.addBuilding(hospital, numAddressStr);
            System.out.println("Successfully added!");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeBuildings() {
        String numAddressStr = inputNumAddress();
        try {
            street.removeBuilding(numAddressStr);
            System.out.println("Successfully removed!");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void findShops() {
        String numAddressStr = inputNumAddress();
        int range = Input.inputInteger(
                "Please enter the range for address: ",
                "Invalid number range, need from " + Street.MIN_NUMBER_ADDRESS + " to " + Street.MAX_NUMBER_ADDRESS,
                Street.MIN_NUMBER_ADDRESS,
                Street.MAX_NUMBER_ADDRESS
        );

        ShopUtils.displayTypeShops();
        int maxValue = TypeShop.values().length;
        int typeShopNum = Input.inputInteger(
                "Please enter the number type shop: ",
                "Invalid number type shop, need from 1 to " + maxValue,
                1,
                maxValue
        );
        TypeShop typeShop = TypeShop.values()[typeShopNum - 1];

        // show
        List<IBuilding> nearbyShops = street.findShopsNearby(numAddressStr, range, typeShop);
        if (nearbyShops.isEmpty()) {
            System.out.println("No shops found around residential house!");
        }
        else {
            System.out.println("Shops around residential house:");
            for (IBuilding nearbyShop : nearbyShops) {
                nearbyShop.show();
            }
        }
    }


    private String inputNumAddress() {
        int numAddress = Input.inputInteger(
                "Please enter the number address: ",
                "Invalid number address, need from " + Street.MIN_NUMBER_ADDRESS + " to " + Street.MAX_NUMBER_ADDRESS,
                Street.MIN_NUMBER_ADDRESS,
                Street.MAX_NUMBER_ADDRESS
        );
        return String.valueOf(numAddress);
    }
}
