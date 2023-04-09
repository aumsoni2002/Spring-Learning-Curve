package com.practice.cruddemo.dao;

import com.practice.cruddemo.entity.Student;

public interface StudentDAO {                // Creating an Interface by the name 'StudentDAO'.
    void save(Student theStudent);            // defining a method 'save' which takes an object of type 'Student' as an argument.
}
