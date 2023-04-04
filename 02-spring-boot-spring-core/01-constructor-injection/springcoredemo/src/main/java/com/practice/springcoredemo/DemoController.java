package com.practice.springcoredemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    // here I have defined a private field for the dependency.
    // Now this 'myCoach' is of type 'Coach' interface, because we can save an instance of type 'Coach' and use its methods.
    private Coach myCoach;

    // For dependency injection, Spring can use auto wiring
    // Spring will look for a class that matches by type: class or interface
    // Spring will inject it automatically ... hence it is autowired

    // This is Constructor Injection Method
    @Autowired
    public DemoController(Coach theCoach) {     // here we are sending an instance 'theCoach' of type 'Coach'
        this.myCoach = theCoach; // here we are saving that instance into 'myCoach' variable so that we can use methods of 'Coach'
    }

    // here we are exposing a route and once we open the page with this route, the function 'getDailyWorkout()' will be called.
    // And it will send back the function 'getDailyWorkout()' which was implemented in the 'CricketCoach' class.
    @GetMapping("/dailyworkout")
    public String getDailyWorkout(){
        return myCoach.getDailyWorkout();   // here as we made the variable 'myCoach' of type 'Coach', we were able to use its methods.
    }
}


