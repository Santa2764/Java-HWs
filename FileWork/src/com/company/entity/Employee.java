package com.company.entity;

public class Employee  implements Cloneable {
    private final String name;
    private final String surname;
    private final int age;
    private int experience;

    public Employee(String name, String surname, int age, int experience) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.experience = experience;
    }

    @Override
    public Employee clone() {
        try {
            return (Employee) super.clone();
        }
        catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public void show() {
        System.out.println(name + " - " + surname + " - " + age + " - " + experience);
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
}
