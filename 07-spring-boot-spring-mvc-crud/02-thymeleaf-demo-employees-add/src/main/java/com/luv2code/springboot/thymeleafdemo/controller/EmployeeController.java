package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
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

        return "employees/list-employees";
    }


    // add mapping for "/showFormForAdd"
    @GetMapping("/showFormForAdd")
    public String showFormForAdd (Model theModel) {
        // create model attribute to bind form data (before we show the form, we must have to add a model attribute
        // which is an object that will hold the form data for the data-binding)
        Employee theEmployee = new Employee();
        theModel.addAttribute("employee", theEmployee);
        return "employees/employee-form"; 			// here it will look for src/main/resources/templates/employees/employee-form.html
    }


    // add post mapping for "save"
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
        // Save the Employee
        employeeService.save(theEmployee);

        // use a redirect to prevent duplicate submissions
        return "redirect:/employees/list";
    }
}









