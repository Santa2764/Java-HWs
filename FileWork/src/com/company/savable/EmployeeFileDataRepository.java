package com.company.savable;

import com.company.entity.Employee;
import com.company.interfaces.IDataRepository;
import com.company.utils.FileWork;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class EmployeeFileDataRepository implements IDataRepository<Employee> {
    private static final String FILENAME = "employees.txt";
    private static final Path PATH_FILE = Path.of(".", FILENAME);
    private static final String SEPARATOR = "|";

    @Override
    public boolean save(List<Employee> obj) {
        try {
            FileWork.clearFile(PATH_FILE);
        }
        catch (IOException e) {
            return false;
        }

        boolean isAllSave = true;
        for (Employee emp : obj) {
            StringBuilder builderStr = new StringBuilder();

            builderStr.append(emp.getName()).append(SEPARATOR);
            builderStr.append(emp.getSurname()).append(SEPARATOR);
            builderStr.append(emp.getAge()).append(SEPARATOR);
            builderStr.append(emp.getExperience());

            try {
                FileWork.appendStrToFile(PATH_FILE, builderStr.toString());
            }
            catch (IOException e) {
                isAllSave = false;
            }
        }
        return isAllSave;
    }

    @Override
    public List<Employee> getAll() {
        List<String> employeesStr = FileWork.getStringsFromFile(PATH_FILE.toFile());
        if (employeesStr == null) {
            return null;
        }

        List<Employee> employees = new ArrayList<>();
        for (String s : employeesStr) {
            String[] employeeStr = s.split("\\" + SEPARATOR);
            employees.add(new Employee(
                    employeeStr[0],
                    employeeStr[1],
                    Integer.parseInt(employeeStr[2]),
                    Integer.parseInt(employeeStr[3])
            ));
        }

        return employees;
    }
}
