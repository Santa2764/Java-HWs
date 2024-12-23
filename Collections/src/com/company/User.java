package com.company;

import java.util.Objects;

public class User {
    private String name;
    private int age;
    private String phone;

    public User() {}

    public User(String name, int age, String phone) {
        this.name = name;
        this.age = age;
        this.phone = phone;
    }

    /*@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        User user = (User)obj;
        return Objects.equals(name, user.name) &&
               Objects.equals(age, user.age) &&
               Objects.equals(phone, user.phone);
    }*/

    @Override
    public int hashCode() {
        return Objects.hash(name, age, phone);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                '}';
    }
}
