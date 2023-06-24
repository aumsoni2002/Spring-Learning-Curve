package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    // load employee data
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService theEmployeeService) { // Constructor Injection
        employeeService = theEmployeeService;
    }

    // add mapping for "/list"

    @GetMapping("/list")
    public String listEmployees(Model theModel) {

        // Getting the list of employees from database through the findAll() Method of EmployeeService
        List<Employee> theEmployees = employeeService.findAll();

        // add to the spring model
        theModel.addAttribute("employees", theEmployees);

        return "list-employees";
    }
}









