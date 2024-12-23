package com.company.enums;

public enum TypeShop {
    SMALL(1),
    MEDIUM(3),
    LARGE(5);

    private final int departmentCount;

    TypeShop(int departmentCount) {
        this.departmentCount = departmentCount;
    }

    public int getDepartmentCount() {
        return departmentCount;
    }
}
