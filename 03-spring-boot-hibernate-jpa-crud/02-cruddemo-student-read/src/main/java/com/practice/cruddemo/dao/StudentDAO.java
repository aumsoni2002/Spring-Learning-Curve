package com.practice.cruddemo.dao;

import com.practice.cruddemo.entity.Student;

public interface StudentDAO {               // Creating an Interface by the name 'StudentDAO'.
    void save(Student theStudent);          // defining a method 'save' which takes an object of type 'Student' as an argument.
    Student findById(Integer id);           // defining a method 'findById' which takes 'id' of type 'Integer' as an argument and returns an object of type student
}
