package com.practice.demosecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/showMyLoginPage")     // this URL must match the DemoSecurityConfig's loginPage URL
    public String showMyLoginPage() {
        // return "plain-login";        // src/main/resources/templates/plain-login.html
        return "fancy-login";           // src/main/resources/templates/fancy-login.html
    }
}
