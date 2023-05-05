package com.practice.cruddemo.rest;

import com.practice.cruddemo.dao.EmployeeDAO;
import com.practice.cruddemo.entity.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    // here we have created a private field 'employeeDAO' of type 'EmployeeDAO' so that we can assign an instance
    // of type 'EmployeeDAO' through a constructor.
    private EmployeeDAO employeeDAO;

    public EmployeeRestController(EmployeeDAO theEmployeeDAO) { // here we have created a constructor which take an object(instance) of type 'EmployeeDAO' as an argument
        this.employeeDAO = theEmployeeDAO; // here we are saving the coming new instance to the field 'employeeDAO' so that we can use methods of it.
    }

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeDAO.findAll(); // here we are using the method 'findAll' of the class 'EmployeeDAO'
    }
}
