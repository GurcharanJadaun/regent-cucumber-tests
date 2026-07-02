package com.regent.utils;

public class StudentData {

    public final String studentId;
    public final String firstName;
    public final String lastName;
    public final String aidYear;

    public StudentData(String studentId, String firstName, String lastName, String aidYear) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName  = lastName;
        this.aidYear   = aidYear;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return String.format("Student[id=%s, name=%s, aidYear=%s]", studentId, getFullName(), aidYear);
    }
}
