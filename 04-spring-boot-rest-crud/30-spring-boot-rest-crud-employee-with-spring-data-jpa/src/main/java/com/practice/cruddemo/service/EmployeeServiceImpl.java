package com.practice.cruddemo.service;

import com.practice.cruddemo.dao.EmployeeRepository;
import com.practice.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
        employeeRepository = theEmployeeRepository;
    }

    @Override
    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int theId) {
        // here Optional is to let spring know that the coming value can be empty that is 'null' too.
        Optional<Employee> result = employeeRepository.findById(theId);
        Employee theEmployee = null; // here we are creating a variable of value 'null'
        if(result.isPresent()){ // here we are checking if the value of 'result' is not null than save that value into 'theEmployee'.
            theEmployee = result.get();
        }
        else { // if the value of 'result' is null than return below exception message.
            throw new RuntimeException("Employee with id " + theId + " is not found.");
        }
        return theEmployee; // If there is value in 'result'  than save it in 'theEmployee' than return it.
    }

    @Override
    public Employee save(Employee theEmployee) {
        return employeeRepository.save(theEmployee);
    }

    @Override
    public void deleteById(int theId) {
        employeeRepository.deleteById(theId);
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