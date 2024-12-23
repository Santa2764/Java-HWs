package com.company.entity;

import com.company.enums.TypeShop;
import com.company.interfaces.IBuilding;
import com.company.tests.TestUtils;

import java.util.*;

public class Street {
    public static final int MIN_NUMBER_ADDRESS = 1;
    public static final int MAX_NUMBER_ADDRESS = 100;

    private String title;
    private static TreeSet<String> HouseNumbers;
    private TreeSet<IBuilding> buildings;

    static {
        HouseNumbers = new TreeSet<>();
    }

    public Street(String title, TreeSet<IBuilding> buildings) {
        this.title = title;
        this.buildings = buildings;
    }


    public static void AddHouseNumber(String houseNumber) {
        HouseNumbers.add(houseNumber);
    }

    public static Set<String> GetHouseNumbers() {
        return Collections.unmodifiableSet(HouseNumbers);
    }


    public void addBuilding(IBuilding building, String numberHouse) throws Exception {
        if (numberHouse == null || numberHouse.isEmpty()) {
            throw new IllegalArgumentException("House number must not be null or empty...");
        }

        String uniqueHouseNumber = TestUtils.generateUniqueHouseNumber(numberHouse, GetHouseNumbers());
        if (HouseNumbers.add(uniqueHouseNumber)) {
            building.setAddress(uniqueHouseNumber);
            buildings.add(building);
        }
        else {
            throw new Exception("House number already exists: " + uniqueHouseNumber + "...");
        }
    }

    public void removeBuilding(String numberHouse) throws Exception {
        if (numberHouse == null || numberHouse.isEmpty()) {
            throw new IllegalArgumentException("House number must not be null or empty...");
        }

        if (HouseNumbers.contains(numberHouse)) {
            IBuilding buildingToRemove = null;
            for (IBuilding building : buildings) {
                if (building.getAddress().equals(numberHouse)) {
                    buildingToRemove = building;
                    break;
                }
            }

            if (buildingToRemove != null) {
                buildings.remove(buildingToRemove);
                HouseNumbers.remove(numberHouse);
            }
            else {
                throw new Exception("Building with house number " + numberHouse + " not found...");
            }
        }
        else {
            throw new Exception("House number does not exist: " + numberHouse + "...");
        }
    }

    public void showInfo() {
        if (buildings != null && !buildings.isEmpty()) {
            for (IBuilding building : buildings) {
                building.show();
            }
        }
        else {
            System.out.println("House numbers are empty...");
        }
    }

    public List<IBuilding> findShopsNearby(String addressResidentialHouse, int range, TypeShop typeShop) {
        // для выбранного жилого дома находит в заданной окрестности
        // (определенное количество домов от адреса дома) все магазины, имеющие отдел заданного типа

        List<IBuilding> nearbyShops = new ArrayList<>();

        int addressNumber;
        try {
            addressNumber = Integer.parseInt(addressResidentialHouse.replaceAll("\\D+", ""));
        }
        catch (NumberFormatException e) {
            System.out.println("Invalid address format: " + addressResidentialHouse);
            return nearbyShops;
        }

        // границы диапазона
        int lowerBound = Math.max(addressNumber - range, 0);
        int upperBound = addressNumber + range;

        // проходимся по всем домам
        for (IBuilding building : buildings) {
            // если это магазин
            if (building instanceof Shop shop) {
                String shopAddress = shop.getAddress();
                int shopNumber;

                // парсим номер дома (магазина)
                try {
                    shopNumber = Integer.parseInt(shopAddress.replaceAll("\\D+", ""));
                }
                catch (NumberFormatException e) {
                    continue;
                }

                // если попадает под диапазон номеров и под тип магазина, то нашли
                if (shopNumber >= lowerBound && shopNumber <= upperBound) {
                    if (shop.getTypeShop() == typeShop) {
                        nearbyShops.add(shop);
                    }
                }
            }
        }

        return nearbyShops;
    }

    public int getCountBuildings() {
        return buildings.size();
    }
}
