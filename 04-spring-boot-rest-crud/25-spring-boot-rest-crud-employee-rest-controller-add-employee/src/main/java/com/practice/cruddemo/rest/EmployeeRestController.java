package com.practice.cruddemo.rest;

import com.practice.cruddemo.entity.Employee;
import com.practice.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    // here we have created a private field 'employeeDAO' of type 'EmployeeDAO' so that we can assign an instance
    // of type 'EmployeeDAO' through a constructor.
    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService) { // here we have created a constructor which take an object(instance) of type 'EmployeeService' as an argument
        this.employeeService = theEmployeeService; // here we are saving the coming new instance to the field 'employeeService' so that we can use methods of it.
    }

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll(); // here we are using the method 'findAll' of the class 'EmployeeService'
    }

    // add mapping for GET: /employees/{employeeId}
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){
        Employee theEmployee = employeeService.findById(employeeId);

        if(theEmployee == null){
            throw new RuntimeException("Employee with id: " + employeeId + " is not found...");
        }

        return theEmployee;
    }

    // add mapping for POST: /employees
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee){
        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update
        theEmployee.setId(0);

        Employee dbEmployee = employeeService.save(theEmployee);

        return dbEmployee;
    }
}

/*
How EmployeeRestController is connected to Database: Explained in simple terms

The 'EmployeeRestController' has a private field 'employeeService' of type 'EmployeeService' class.
The 'EmployeeRestController' has a constructor which is injecting 'EmployeeService' by saving the 'theEmployeeService' into
private field 'employeeService' to use its methods.
The 'EmployeeRestController' has a public method 'findAll' which is calling the method 'findAll' of the class 'EmployeeService'

The EmployeeService is just an interface which has methods declared(methods without body) in it.

The 'EmployeeServiceImpl' is a class which implements 'EmployeeService' so that we can write all of its method's body.
The 'EmployeeServiceImpl' has a private field 'employeeDAO' of type 'EmployeeDAO' class.
The 'EmployeeServiceImpl' has a constructor which is injecting 'EmployeeDAO' by saving the 'theEmployeeDAO' into
private field 'employeeDAO' to use its methods.
The 'EmployeeServiceImpl' has a public method 'findAll' which is calling the method 'findAll' of the class 'EmployeeDAO'

The EmployeeDAO is just an interface which has methods declared(methods without body) in it.

The 'EmployeeDAOJpaImpl' is a class which implements 'EmployeeDAO' so that we can write all of its method's body.
The 'EmployeeDAOJpaImpl' has a private field 'entityManager' of type 'EntityManager' class.
The 'EmployeeDAOJpaImpl' has a constructor which is injecting 'EntityManager' by saving the 'theEntityManager' into
private field 'entityManager' to use its methods.
The 'EmployeeDAOJpaImpl' has a public method 'findAll' which has code for getting a list of employees from the database
and returning it in the form of a List to whenever it gets called.
*/