package com.practice.cruddemo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employee")     // here we are declaring/mapping our 'Employee' java class as an Entity Class.
public class Employee {

    // Defining Fields
    @Id                        // here we are declaring our 'id' field as the Unique Identifier of this Entity Class
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // here what we are saying is that this id value will be generated by the database and managed by the database.
    // There's no need for our code to manually try and keep track of that given id.
    @Column(name = "id") // here we are mapping our 'id' field of java class to the 'id' column of the Database table.
    private int id;

    @Column(name = "first_name")
    // here we are mapping our 'firstName' field of java class to the 'first_name' column of the Database table.
    private String firstName;

    @Column(name = "last_name")
    // here we are mapping our 'lastName' field of java class to the 'last_name' column of the Database table.
    private String lastName;

    @Column(name = "email")
    // here we are mapping our 'email' field of java class to the 'email' column of the Database table.
    private String email;


    // Defining Constructor
    public Employee() {
    }

    public Employee(String firstName, String lastName, String email) {
        // here I do not have put the id, because it is going to be generated by the database.
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }


    // Defining Getters/Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    // Defining toString method
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}