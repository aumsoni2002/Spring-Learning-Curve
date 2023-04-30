package com.practice.restcrud.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController                // this will add REST Support
@RequestMapping("/test")       // this will add Mapping Support, So every URL must contain '/test' before any specific route.
public class DemoRestController {
    @GetMapping("/hello")			// Handles HTTP GET requests, so we can access the REST endpoint at /test/hello
    public String sayHello() {
        return "Hello World!";		// Returns content to client
    }
}
