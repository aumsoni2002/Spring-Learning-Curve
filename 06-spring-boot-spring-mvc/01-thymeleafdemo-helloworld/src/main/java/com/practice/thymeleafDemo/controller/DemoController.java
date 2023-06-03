package com.practice.thymeleafDemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {
    @GetMapping("/hello")
    public String sayHello(Model theModel) {
        // this attribute 'theDate' will be accessed by Thymeleaf HTML Template from this Spring MVC Model
        theModel.addAttribute("theDate", new java.util.Date());

        // As we inject Thymeleaf dependency in Maven POM, Spring will autoconfigure to use Thymeleaf and
        // look for 'helloworld.html' file in src/main/resources/templates/
        return "helloworld";
    }
}
