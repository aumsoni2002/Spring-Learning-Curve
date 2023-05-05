package com.practice.cruddemo.dao;

import com.practice.cruddemo.entity.Employee;
import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();                // here we are creating method 'findAll' which will return list of employees

    Employee findById(int theId);           // returning a single employee by looking for its id

    Employee save(Employee theEmployee);    // Saving a new employee or updating an existing employee into database

    void deleteById(int theId);         // delete an employee from the database
}
