package com.practice.springcoredemo.rest;

import com.practice.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    private Coach myCoach;
    @Autowired
    public DemoController(@Qualifier("baseballCoach")Coach theCoach) {
        this.myCoach = theCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }
}


/*
• How it Works?
In our project, we have an interface 'Coach' and only one class 'CricketCoach' that implements 'Coach'. So it easy straight forward for spring to inject
dependency if there is only one class that is implementing 'Coach'.
Now consider that we have multiple classes that is implementing 'Coach' interface, Now how spring will know which class to choose.

If we have classes such as 'CricketCoach', 'BaseballCoach', 'TrackCoach' and 'TennisCoach', The Spring will show us below error when we run it:
 Parameter 0 of constructor in com.luv2code.springcoredemo.rest.DemoController
 required a single bean, but 4 were found:
 baseballCoach
 cricketCoach
 tennisCoach
 trackCoach

Now the solution here is to make use of ' @Qualifier("className") ' in the autowired constructor or setter function.
// File: DemoController.java (Constructor Example)
@Autowired
public DemoController(@Qualifier("cricketCoach") Coach theCoach) {// here we are telling spring tha make use of 'cricketCoach' class as the injection. While implementing 'Coach' interface.
myCoach = theCoach;
}

// File: DemoController.java (Setter Example)
@Autowired
public void setMyCoach(@Qualifier("cricketCoach") Coach theCoach) {// here we are telling spring tha make use of 'cricketCoach' class as the injection. While implementing 'Coach' interface.
myCoach = theCoach;
}

Important Note: Now when we put class name in those curly brackets right after Qualifier annotation, the first letter of the
class must be lower-case.
*/