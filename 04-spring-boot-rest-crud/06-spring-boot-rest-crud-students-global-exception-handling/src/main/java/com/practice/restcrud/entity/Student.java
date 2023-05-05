package com.practice.restcrud.entity;

public class Student {

    // Private Fields/Variables
    private String firstName;
    private String lastName;

    // Constructor: helps in instantiating a new student object and setting values of fields in that object
    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Getters - Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
