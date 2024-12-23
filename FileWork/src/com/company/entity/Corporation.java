package com.company.entity;

import com.company.interfaces.IPrintable;
import com.company.interfaces.IDataRepository;
import com.company.printable.FullCorporationPrintable;
import com.company.savable.EmployeeFileDataRepository;
import com.company.utils.ListWork;

import java.util.ArrayList;
import java.util.List;

public class Corporation {
    private final String title;
    private List<Employee> employees;
    private IPrintable printable;
    private IDataRepository<Employee> repository;

    public Corporation(String title, List<Employee> employees) {
        this(title, employees, new FullCorporationPrintable(), new EmployeeFileDataRepository());
    }

    public Corporation(String title, List<Employee> employees, IPrintable printable, IDataRepository<Employee> repository) {
        this.title = title;
        setEmployees(employees);
        setPrintable(printable);
        setRepository(repository);
    }


    public List<Employee> getByAge(int age) {
        return ListWork.getFilteredList(employees, e -> e.getAge() == age);
    }

    public List<Employee> getBySurname(String firstSymbol) {
        return ListWork.getFilteredList(employees,
                e -> e.getSurname().toLowerCase().startsWith(firstSymbol.toLowerCase())
        );
    }

    public void editByNumber(int num, Employee employee) {
        if (num < 1 || num > employees.size()) {
            throw new IllegalArgumentException("Employee number is incorrect...");
        }
        Employee employeeToEdit = employees.get(num - 1);
        employeeToEdit.setExperience(employee.getExperience());
    }

    public void deleteByNumber(int num) {
        if (num < 1 || num > employees.size()) {
            throw new IllegalArgumentException("Employee number is incorrect...");
        }
        Employee employeeToDelete = employees.get(num - 1);
        employees.remove(employeeToDelete);
    }

    public Employee searchBySurname(String surname) {
        return ListWork.getElement(employees, e -> e.getSurname().equalsIgnoreCase(surname));
    }

    public boolean save() {
        return repository.save(employees);
    }

    public void loadFromRepository() {
        List<Employee> employeesFromRep = repository.getAll();
        if (employeesFromRep != null && !employeesFromRep.isEmpty()) {
            employees = employeesFromRep;
        }
    }

    public void show() {
        printable.print(this);
    }


    public void setPrintable(IPrintable printable) {
        if (printable != null) {
            this.printable = printable;
        }
    }

    public void setRepository(IDataRepository<Employee> repository) {
        if (repository != null) {
            this.repository = repository;
        }
    }

    public void setEmployees(final List<Employee> employees) {
        this.employees = employees != null ? employees : new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public List<Employee> getEmployees() {
        return new ArrayList<>(employees);
    }

    public int getCountEmployees() {
        return employees.size();
    }
}
