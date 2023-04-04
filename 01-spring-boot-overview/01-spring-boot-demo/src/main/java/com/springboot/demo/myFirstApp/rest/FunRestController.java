package com.springboot.demo.myFirstApp.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController                     // this annotation sets up a rest controller
public class FunRestController {
    // here we will right a code where the string "Hello World" will be shown on the root '/'.
    @GetMapping("/")                // Handles HTTP GET Request
    public String sayHello() {
        return "Hello World!!";
    }
}
