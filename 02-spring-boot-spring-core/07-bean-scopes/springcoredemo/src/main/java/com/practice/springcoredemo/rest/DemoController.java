package com.practice.springcoredemo.rest;

import com.practice.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    private Coach myCoach;      // first variable for referencing an instance of 'CricketCoach' class/bean.
    private Coach anotherCoach; // second variable for referencing the same above-mentioned instance of 'CricketCoach' class/bean.

    private Coach thirdCoach; // third variable for referencing a new instance of the 'BaseballCoach' class/bean.
    private Coach fourthCoach; // fourth variable for referencing another new instance(not above one) of the 'BaseballCoach' class/bean.

    @Autowired
    public DemoController(@Qualifier("cricketCoach") Coach theCoach,            // points at same instance of 'CricketCoach'.
                          @Qualifier("cricketCoach") Coach theAnotherCoach,     // points at same instance of 'CricketCoach'.
                          @Qualifier("baseballCoach") Coach thirdCoach, // points at an instance of 'BaseballCoach'
                          @Qualifier("baseballCoach") Coach fourthCoach) { // points at another instance of 'BaseballCoach'

        this.myCoach = theCoach;
        this.anotherCoach = theAnotherCoach;
        this.thirdCoach = thirdCoach;
        this.fourthCoach = fourthCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }

    @GetMapping("/checkScopeType1")
    public String checkScopeType() {
        // this will give us 'true' as we have specified singleton scope to 'CricketCoach' for referencing 'myCoach' and 'anotherCoach'.
        return "Comparing beans: myCoach == anotherCoach: " + (myCoach == anotherCoach);
    }

    @GetMapping("/checkScopeType2")
    public String checkScopeType2() {
        // this will give us 'false' as we have specified prototype scope to 'BaseballCoach' for referencing 'thirdCoach' and 'fourthCoach'.
        return "Comparing beans: thirdCoach == fourthCoach: " + (thirdCoach == fourthCoach);
    }
}

/*
As we are specifying 'Singleton Scope' for 'CricketCoach' bean:
The Spring will only create one instance of the 'CricketCoach', save it in memory at one location.
And all references such as myCoach, anotherCoach etc. They all will point to that location where that one instance of 'CricketCoach'
has been created.
Singleton Scope: Creates a one shared instance of a bean which will be point at, by every reference(variables).
*/

/*
As we are specifying 'Prototype Scope' for 'BaseballCoach' bean:
The Spring will create a new instance of the 'BaseballCoach' for every unique references. And save it in memory at unique locations.
All the references such as thirdCoach, fourthCoach etc. They all will point to that unique locations where those instances have been
created.
Prototype Scope: Creates a unique instance of a bean everytime for all unique references to point at.
*/