package com.company.menu;

import com.company.entity.Corporation;
import com.company.entity.Employee;
import com.company.utils.Input;

import java.util.List;

public class CorporationMenu {
    private final Corporation corporation;

    public CorporationMenu(Corporation corporation) {
        this.corporation = corporation;
    }


    public void show() {
        System.out.println("Corporation menu:");
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
                showByAge();
                break;

            case 3:
                showBySurname();
                break;

            case 4:
                edit();
                break;

            case 5:
                delete();
                break;

            case 6:
                searchBySurname();
                break;

            case 7:
                save();
                break;

            default:
                return false;
        }
        return true;
    }


    private void showAll() {
        corporation.show();
    }

    private void showByAge() {
        int age = Input.inputInteger(
                "Input age to find: ",
                "Age must be between 18 and 30...",
                18,
                30
        );
        showEmployees(corporation.getByAge(age));
    }

    private void showBySurname() {
        String firstSymbol = Input.inputString(
                "Input first symbol for surname to find: ",
                "String must be not empty..."
        );
        showEmployees(corporation.getBySurname(firstSymbol));
    }

    private void edit() {
        int maxValue = corporation.getCountEmployees();
        int empNum = Input.inputInteger(
                "Input employee number to edit: ",
                "Number must be between 1 and " + maxValue + "...",
                1,
                maxValue
        );
        int experience = Input.inputInteger(
                "Input employee experience: ",
                "Experience must be between 1 and 10...",
                1,
                10
        );

        Employee employee = corporation.getEmployees().get(empNum - 1).clone();
        employee.setExperience(experience);
        try {
            corporation.editByNumber(empNum, employee);
            System.out.println("The change was successfully saved...");
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void delete() {
        int maxValue = corporation.getCountEmployees();
        int empNum = Input.inputInteger(
                "Input employee number to delete: ",
                "Number must be between 1 and " + maxValue + "...",
                1,
                maxValue
        );
        try {
            corporation.deleteByNumber(empNum);
            System.out.println("The delete was successfully...");
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void searchBySurname() {
        String surname = Input.inputString(
                "Input surname to search: ",
                "Surname must be not empty..."
        );
        Employee employee = corporation.searchBySurname(surname);
        if (employee == null) {
            System.out.println("Employee not found...");
        }
        else {
            System.out.print("Employee: ");
            employee.show();
        }
    }

    private void save() {
        String message = corporation.save() ?
                "All employees has been saved..." :
                "Some employees are not saved...";
        System.out.println(message);
    }


    private void showEmployees(final List<Employee> employees) {
        if (employees.isEmpty()) {
            System.out.println("No employees found...");
            return;
        }
        for (int i = 0; i < employees.size(); i++) {
            System.out.print((i + 1) + ". ");
            employees.get(i).show();
        }
    }
}
