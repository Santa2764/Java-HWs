package com.company.enums;

public enum AccreditalLevel {
    BASIC(10, 20),
    INTERMEDIATE(21, 50),
    ADVANCED(51, 100),
    PROFESSIONAL(101, 200),
    EXPERT(201, 500);

    private final int minStudents;
    private final int maxStudents;

    AccreditalLevel(int minStudents, int maxStudents) {
        this.minStudents = minStudents;
        this.maxStudents = maxStudents;
    }

    public int getMinStudents() {
        return minStudents;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public String getStudentRange() {
        return minStudents + " - " + maxStudents;
    }
}
