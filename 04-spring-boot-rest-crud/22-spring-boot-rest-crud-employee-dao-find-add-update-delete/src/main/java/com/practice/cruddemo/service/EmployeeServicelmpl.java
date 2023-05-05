package com.practice.cruddemo.service;

import com.practice.cruddemo.dao.EmployeeDAO;
import com.practice.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServicelmpl implements EmployeeService{

    private EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServicelmpl(EmployeeDAO theEmployeeDAO){ // here we have created a constructor which take an object(instance) of type 'EmployeeDAO' as an argument
        this.employeeDAO = theEmployeeDAO; // here we are saving the coming new instance to the field 'employeeDAO' so that we can use methods of it.
    }

    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll(); // here we are using the method 'findAll' of the class 'EmployeeDAO'
    }
}

/*
In Java Spring Boot, @Service is a Spring annotation that is used to indicate that a particular class provides business
logic or service functionality.

When a class is annotated with @Service, it is automatically registered as a bean in the Spring application context, and
it can be easily identified and managed by Spring's container.

@Service is typically used on classes that contain application-specific business logic, such as data manipulation,
transformation, or validation, or classes that interact with other services or external systems.

The @Service annotation is also useful for separating business logic from other layers of the application, such as the data
access layer or the presentation layer. This can help improve the maintainability, testability, and overall architecture of
a Spring Boot application.

Overall, @Service is a convenient way to provide a structured and standardized approach to implementing business logic in
a Spring Boot application.
*/