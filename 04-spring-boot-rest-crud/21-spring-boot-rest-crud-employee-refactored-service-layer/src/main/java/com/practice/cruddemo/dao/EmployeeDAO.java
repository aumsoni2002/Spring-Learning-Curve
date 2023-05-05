package com.practice.cruddemo.dao;

import com.practice.cruddemo.entity.Employee;
import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();                // here we are creating method 'findAll' which will return list of employees
}
