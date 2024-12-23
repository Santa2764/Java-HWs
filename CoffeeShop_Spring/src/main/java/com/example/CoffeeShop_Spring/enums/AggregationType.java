package com.example.CoffeeShop_Spring.enums;

public enum AggregationType {
    MIN("MIN"),
    MAX("MAX"),
    AVG("AVG");

    private final String sqlFunction;

    AggregationType(String sqlFunction) {
        this.sqlFunction = sqlFunction;
    }

    public String getSqlFunction() {
        return sqlFunction;
    }
}
