package com.company.entity;

import com.company.interfaces.IBuilding;

import java.util.MissingFormatArgumentException;

public abstract class Building implements IBuilding {
    private String address;

    public Building(String address) {
        setAddress(address);
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        if (address == null || address.isEmpty()) {
            throw new MissingFormatArgumentException("Address must not be empty or null...");
        }
        this.address = address;
    }

    @Override
    public int compareTo(IBuilding other) {
        return this.getAddress().compareTo(other.getAddress());
    }
}
