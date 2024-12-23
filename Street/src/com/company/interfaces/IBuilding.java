package com.company.interfaces;

import com.company.entity.Building;

public interface IBuilding extends Comparable<IBuilding> {
    String getAddress();
    void setAddress(String address);
    void show();
    void setFieldFromStr(String str);
}
