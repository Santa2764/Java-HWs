package com.company;

import com.company.entity.Projector;
import com.company.enums.ProjectorManufacturer;
import com.company.tests.ProjectorFactory;
import com.company.tests.TestUtils;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class Task4 {
    public static void main(String[] args) {
        List<Projector> projectors = ProjectorFactory.getProjectors();

        System.out.println("Count: " + projectors.size() + "\n");
        showProjectors(projectors);

        ProjectorManufacturer manufacturer = ProjectorManufacturer.SONY;
        System.out.println("\nFind by manufacturer <" + manufacturer + ">:");
        showProjectors(getFilteredProjectors(projectors, d -> d.getManufacturer().equals(manufacturer)));

        System.out.println("\nFind by current year <" + TestUtils.CURR_YEAR + ">:");
        showProjectors(getFilteredProjectors(projectors, d -> d.getManufactureYear() == TestUtils.CURR_YEAR));

        float price = 50000f;
        System.out.println("\nFind by price more <" + price + ">:");
        showProjectors(getFilteredProjectors(projectors, d -> d.getPrice() > price));

        System.out.println("\nСортировка по цене по возрастанию:");
        showProjectors(getSortedProjectors(projectors, (d1, d2) -> Double.compare(d1.getPrice(), d2.getPrice())));

        System.out.println("\nСортировка по цене по убыванию:");
        showProjectors(getSortedProjectors(projectors, (d1, d2) -> Double.compare(d2.getPrice(), d1.getPrice())));

        System.out.println("\nСортировка по году по возрастанию:");
        showProjectors(getSortedProjectors(projectors, (d1, d2) -> Integer.compare(d1.getManufactureYear(), d2.getManufactureYear())));

        System.out.println("\nСортировка по году по убыванию:");
        showProjectors(getSortedProjectors(projectors, (d1, d2) -> Integer.compare(d2.getManufactureYear(), d1.getManufactureYear())));
    }

    private static List<Projector> getFilteredProjectors(List<Projector> projectors, Predicate<Projector> filterProjector) {
        return projectors.stream().filter(filterProjector).toList();
    }

    private static List<Projector> getSortedProjectors(List<Projector> projectors, Comparator<Projector> comparator) {
        return projectors.stream().sorted(comparator).toList();
    }

    private static void showProjectors(List<Projector> projectors) {
        projectors.forEach(System.out::println);
    }
}
