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
        System.out.println("In constructor: " + getClass().getSimpleName());
        this.myCoach = theCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }
}


/*
• Initialization
By default, when our application starts, all beans are initialized so it will scan for all components such as @Component and those components will instialized.
So spring will create an instance of each and make them available.

Now to see that all beans have been initialized on the start of our application, we can put a println statement which prints each class's name:
// File: CricketCoach.java
public CricketCoach() {
    System.out.println("In constructor: " + getClass().getSimpleName());
}

// File: BaseballCoach.java
public CricketCoach() {
    System.out.println("In constructor: " + getClass().getSimpleName());
}

// File: TennisCoach.java
public CricketCoach() {
    System.out.println("In constructor: " + getClass().getSimpleName());
}

// File: TrackCoach.java
public CricketCoach() {
    System.out.println("In constructor: " + getClass().getSimpleName());
}

Now once we run the application, all the beans that is our classes which spring converts them into beans once we put the '@Component' will get initialized
and the terminal will show below lines:
In constructor: BaseballCoach
In constructor: CricketCoach
In constructor: TennisCoach
In constructor: TrackCoach

Now there is usually not need of all beans to get initialized at time of application start.
Now to resolve that, we can make use of 'Lazy Initialization' Annotation.

• Lazy Initialization
Instead of creating all beans up front, we can specify lazy initialization
A bean will only be initialized in the following cases:
 It is needed for dependency injection
 Or itis explicitly requested
Add the @Lazy annotation to a given class
Example:
// File: TrackCoach
import org.springframework.context annotation.Lazy;
import org.springframework.stereotype.Component;
@Component
@Lazy						// this will implement Lazy Initialization to this class.
public class TrackCoach implements Coach {
    public TrackCoach() {
        System.out.println("In constructor: " + getClass().getSimpleName());
    }
}


• Lazy Initialization - Global configuration
We can also use this Annotation on all our beans at one time which is with the help of 'application.properties' file.
File: application.properties
spring.main.lazy-initialization=true

Now with above line of declaration, All beans are lazy, no beans will get created until needed, including our DemoController.
If we consider our Coach project, So once we access our REST endpoint of '/dailyworkout', then spring will determine the dependencies
for the DemoController. And for the dependency resolution, spring will create instance of 'CricketCoach' first then create instance
of 'DemoController' and injects the 'CricketCoach'.

• Advantages of using Lazy Initialization
Only create objects as needed
May help with faster startup time if you have large number of components

• Disadvantages
If you have web related components like @RestController, not created until requested
May not discover configuration issues until too late
Need to make sure you have enough memory for all beans once created
*/