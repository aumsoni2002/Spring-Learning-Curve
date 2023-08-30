package com.practice.springmvcdemo;

import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {
    @InitBinder
    // with this annotation, it will pre-process all web requests coming into our controller with below method.
    public void initBinder(WebDataBinder dataBinder) {
        // StringTrimmerEditor is a predefined class in the Spring API which removes white spaces - leading and trailing
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true); // true - if there are only white spaces then trim it till the value gets null
        // here we are saying that for every string from data, bind this stringTrimmerEditor so that it removes the white spaces on every input fields
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/")
    public String showForm(Model theModel) { // the model allows us to share information between Controllers and View Pages(Thymeleaf)
        theModel.addAttribute("customer", new Customer()); // now we can access the 'customer' attribute into our HTML Page
        return "customer-form";
    }

    @PostMapping("/processForm")
    // @Valid is for telling spring mvc to perform validations on those fields which have been set with constraints such as lastName.
    // the 'theBindingResult' will have the result of validation, so that we can know if a field has any error or not.
    public String processForm(@Valid @ModelAttribute("customer") Customer theCustomer, BindingResult theBindingResult) {

        System.out.println("Last Name: |" + theCustomer.getLastName() + "|");
        if (theBindingResult.hasErrors()) { // here we are checking if theBindingResult has any errors
            return "customer-form";
        } else {
            return "customer-confirmation";
        }
    }
}