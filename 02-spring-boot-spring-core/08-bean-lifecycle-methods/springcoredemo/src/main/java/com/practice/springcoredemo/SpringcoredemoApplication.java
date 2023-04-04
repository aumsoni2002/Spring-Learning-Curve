package com.practice.springcoredemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringcoredemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringcoredemoApplication.class, args);
    }
}

/*
-- Bean Lifecyle Methods - Overview

• Bean Lifecycle
Spring Container Started --> beans are instantiated --> dependencies are injected --> internal spring processing occurs --> excutes our custom initialization method
After method gets execute --> Bean is ready to use --> once we use it --> Spring Container gets shutdown --> executes our custom destroy method.

• Bean Lifecycle Methods / Hooks
You can add custom code during bean initialization
 Calling custom business logic methods
 Setting up handles to resources (db, sockets, file etc)
You can add custom code during bean destruction
 Calling custom business logic method
 Clean up handles to resources (db, sockets, files etc)

• Initialization of Method configuration
@Component
public class CricketCoach implements Coach {
    public CricketCoach() {
        System.out.println("In constructor: “ + getClass().getSimpleName());
    }

    @PostConstruct	// With the help of this annotation, we have added our own custom initialization code, once the bean has been constructed.
    public void doMyStartupStuff() {
    System.out.println("In doMyStartupStuff(): " + getClass().getSimpleName());
    }

    @PreDestroy		// With the help of this annotation, we have added our own custom cleanup code, which will run just before the bean gets desctructed.
    public void doMyStartupStuff() {
    System.out.println("In doMyStartupStuff(): " + getClass().getSimpleName());
    }
}
*/