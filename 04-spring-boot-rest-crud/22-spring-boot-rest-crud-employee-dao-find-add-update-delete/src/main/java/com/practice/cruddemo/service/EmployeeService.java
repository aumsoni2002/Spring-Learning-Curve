package com.practice.cruddemo.service;

import com.practice.cruddemo.entity.Employee;
import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();                // here we are creating method 'findAll' which will return list of employees
}
