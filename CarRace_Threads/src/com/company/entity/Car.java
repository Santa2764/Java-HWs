package com.company.entity;

public class Car {
    private static final int MIN_SPEED = 50;
    private static final int MAX_SPEED = 800;

    protected String name;
    protected int maxSpeed;

    public Car(String name, int maxSpeed) {
        this.name = name;
        this.maxSpeed = maxSpeed;
    }

    public String getName() {
        return name;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setName(String name) {
        if (!name.isEmpty()) {
            this.name = name;
        }
    }

    public void setMaxSpeed(int maxSpeed) {
        if (maxSpeed >= MIN_SPEED && maxSpeed <= MAX_SPEED) {
            this.maxSpeed = maxSpeed;
        }
    }
}
