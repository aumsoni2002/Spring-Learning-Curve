package com.springboot.demo.myFirstApp.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController                     // this annotation sets up a rest controller
public class FunRestController {

    // here we will right a code where the string "Hello World" will be shown on the route '/'.
    @GetMapping("/")                // Handles HTTP GET Request
    public String sayHello() {
        return "Hello World!!";
    }

    /*
    Now that we have added devtools into our dependencies, the editor should automatically
    update the webpage once we make changes here in our code.
    It worked completely fine, The Editor was able to re-compile all files by itself after the changes that I made.
    */
    @GetMapping("/workout")
    public String getDailyWorkout() {
        return "Run a hard 5k!!";
    }

    // Let's try to add one more route
    @GetMapping("/fortune")
    public String getDailyFortune() {
        return "Today is your lucky day.";
    }
}
