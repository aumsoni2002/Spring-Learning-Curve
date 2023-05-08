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

    // add mapping for POST: /employees - add a new employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee){
        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update
        theEmployee.setId(0);

        Employee dbEmployee = employeeService.save(theEmployee);

        return dbEmployee;
    }

    // add mapping for PUT: /employees - update an existing employee
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee){
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }

    // add mapping for DELETE: /employees/{employeeId} - delete employee
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){
        Employee toBeDeleteEmp = employeeService.findById(employeeId);

        if(toBeDeleteEmp == null){
            throw new RuntimeException("Employee with id " + employeeId + " is not found.");
        }

        employeeService.deleteById(employeeId);

        return "The Employee with id " + employeeId + " is deleted from the system.";
    }
}

/*
How EmployeeRestController is connected to Database using Spring Data JPA: Explained in simple terms

The 'EmployeeRestController' has a private field 'employeeService' of type 'EmployeeService' class.
The 'EmployeeRestController' has a constructor which is injecting 'EmployeeService' by saving the 'theEmployeeService' into
private field 'employeeService' to use its methods.
The 'EmployeeRestController' has a public method 'findAll' which is calling the method 'findAll' of the class 'EmployeeService'

The EmployeeService is just an interface which has methods declared(methods without body) in it.

The 'EmployeeServiceImpl' is a class which implements 'EmployeeService' so that we can write all of its method's body.
The 'EmployeeServiceImpl' has a private field 'employeeRepository' of type 'EmployeeRepository' class.
The 'EmployeeServiceImpl' has a constructor which is injecting 'EmployeeRepository' by saving the 'theEmployeeRepository' into
private field 'employeeRepository' to use its methods.
The 'EmployeeServiceImpl' has a public method 'findAll' which is calling the method 'findAll' of the class 'EmployeeRepository'

The EmployeeRepository is just an interface.

The 'EmployeeRepository' is an interface which extends JpaRepository so that we can use all of JpaRepository's methods.
The 'JpaRepository' has all methods that is used to implement CRUD Operations.
That is why we do not have to write any extra code in 'EmployeeRepository'.
The necessary action is to enter Entity type and Primary Key type in <...> brackets.

To use all these methods of 'JpaRepository', we need to inject 'EmployeeRepository' into our 'EmployeeServiceImpl' which I explained
in above lines.
*/