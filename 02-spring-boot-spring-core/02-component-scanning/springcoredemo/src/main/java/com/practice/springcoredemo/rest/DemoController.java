package com.practice.springcoredemo.rest;

import com.practice.util.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    private Coach myCoach;

    @Autowired
    public DemoController(Coach theCoach) {
        this.myCoach = theCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }
}

/*
As we see here this 'DemoController.java file is in a subpackage 'rest'. By default, Spring Boot starts component scanning
from same package as your main Spring Boot application which is 'springcoredemo' and it also scans sub-packages recursively.
*/