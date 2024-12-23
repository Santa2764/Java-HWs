package com.company.printable.hospital;

import com.company.entity.Hospital;

public class BriefHospitalPrintable implements IHospitalPrintable {
    @Override
    public void show(Hospital hospital) {
        String sep = "-".repeat(15);
        System.out.println(sep + " Hospital " + sep);
        System.out.println("Address: " + hospital.getAddress());
    }
}
