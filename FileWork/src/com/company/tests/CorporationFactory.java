package com.company.tests;

import com.company.entity.Corporation;
import com.company.entity.Employee;

import java.util.List;

public class CorporationFactory {
    public static Corporation getCorporation() {
        String title = TestUtils.getRandomTitle();
        List<Employee> employees = EmployeeFactory.getEmployees();

        return new Corporation(title, employees);
    }
}
