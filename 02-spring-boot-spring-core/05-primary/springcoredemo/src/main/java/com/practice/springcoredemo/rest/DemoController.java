package com.practice.springcoredemo.rest;

import com.practice.springcoredemo.common.Coach;
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
• How it works?
Instead of putting the 'Qualifier' Annotation and specifing which class should be used.
We can just put the 'Primary' Annotation right above the Class which we wanted to be get used.
Example:
// File: TrackCoach.java
import org.springframework.context .annotation.Primary;
import org.springframework.stereotype.Component;
@Component
@Primary						// with this annotation, the spring will know that it has to use this class as an injection.
public class TrackCoach implements Coach {
    @override
    public String getDailyWorkout() {
    return "Run a hard 5k!";
    }
}

• If 'Primary' Annotation used more than once
The Primary Annotation can only be used once in case of Multiple Impletation.
If we try to mark multiple classes with Primary Annotation, we will get below error:
Unsatisfied dependency expressed through constructor parameter 0:
No qualifying bean of type 'com.luv2code.springcoredemo.common.Coach' available:
more than one ‘primary' bean found among candidates:
[baseballCoach, cricketCoach, tennisCoach, trackCoach]

• Mixing @Primary and @Qualifier
Firstly we can use both annotations at same time. But you might have to be careful which to use when.
Secondly, @Qualifier has higher priority that is if we used @Qualifier for 'CricketCoach' and @Primary for 'TrackCoach', Than Spring will go and use 'CricketCoach'.

• Which one: @Primary or @Qualifier?
@Primary leaves it up to the implementation classes
 Could have the issue of multiple @Primary classes leading to an error
@Qualifier allows to you be very specific on which bean you want
In general, I recommend using @Qualifier
 It is more specific.
 It has higher priority.
*/