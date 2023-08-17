package com.practice.springmvcdemo;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {
    @GetMapping("/")
    public String showForm(Model theModel) { // the model allows us to share information between Controllers and View Pages(Thymeleaf)
        theModel.addAttribute("customer", new Customer()); // now we can access the 'customer' attribute into our HTML Page
        return "customer-form";
    }

    @PostMapping("/processForm")
    // @Valid is for telling spring mvc to perform validations on those fields which have been set with constraints such as lastName.
    // the 'theBindingResult' will have the result of validation, so that we can know if a field has any error or not.
    public String processForm(@Valid @ModelAttribute("customer") Customer theCustomer, BindingResult theBindingResult) {
        if (theBindingResult.hasErrors()) { // here we are checking if theBindingResult has any errors
            return "customer-form";
        } else {
            return "customer-confirmation";
        }
    }
}
