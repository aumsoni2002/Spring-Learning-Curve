package com.practice.cruddemo.dao;

import com.practice.cruddemo.entity.Student;

import java.util.List;

public interface StudentDAO {               // Creating an Interface by the name 'StudentDAO'.
    void save(Student theStudent);          // defining a method 'save' which takes an object of type 'Student' as an argument.

    Student findById(Integer id);           // defining a method 'findById' which takes 'id' of type 'Integer' as an argument and returns an object of type student

    List<Student> findAll();                // here we are creating method 'findAll' which will return list of students

    // here we are creating method 'findByLastName' which will return list of students who have the last name as same as we enter
    List<Student> findByLastName(String theLastName);

    void update(Student theStudent);        // here we are creating method 'update' which will take an object 'theStudent' of type 'Student'.

    void delete(Integer id);    // here we are creating method 'delete' which will take 'id' of type 'Integer' as an argument.
}
