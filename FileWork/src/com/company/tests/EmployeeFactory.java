package com.company.tests;

import com.company.entity.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeFactory {
    public static Employee getEmployee() {
        String name = TestUtils.getRandomName();
        String surname = TestUtils.getRandomSurname();
        int age = TestUtils.getRandomInteger(18, 30);
        int experience = TestUtils.getRandomInteger(1, 20);

        return new Employee(name, surname, age, experience);
    }

    public static List<Employee> getEmployees() {
        int count = TestUtils.getRandomInteger(10, 15);
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            employees.add(getEmployee());
        }
        return employees;
    }
}
