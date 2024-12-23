package com.company.printable;

import com.company.entity.Corporation;
import com.company.entity.Employee;
import com.company.interfaces.IPrintable;

import java.util.List;

public class FullCorporationPrintable implements IPrintable {
    @Override
    public void print(Corporation corporation) {
        System.out.println("--------- Full Corporation --------");
        System.out.println("Title: " + corporation.getTitle());

        if (corporation.getCountEmployees() == 0) {
            System.out.println("No employees");
        }
        else {
            System.out.println("Employees:");
            List<Employee> employees = corporation.getEmployees();
            for (int i = 0; i < employees.size(); i++) {
                System.out.print((i + 1) + ". ");
                employees.get(i).show();
            }
        }
    }
}
