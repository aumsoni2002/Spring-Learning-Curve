package com.practice.springcoredemo.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;


@Component
public class CricketCoach implements Coach {
    public CricketCoach() {
        System.out.println("In constructor: " + getClass().getSimpleName());
    }

    // Below is our custom init method
    @PostConstruct
    // With the help of this annotation, we have added our own custom initialization code,
    // which will run just after the bean 'CricketCoach' has been constructed.
    public void doMyStartupStuff() {
        System.out.println("In doMyStartupStuff(): " + getClass().getSimpleName());
    }

    // Below is our custom destroy method
    @PreDestroy
    // With the help of this annotation, we have added our own custom cleanup code, which will run just before the bean gets destructed.
    public void doMyCleanupStuff() {
        System.out.println("In doMyCleanupStuff(): " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Practice Fast Bowling for 15 Minutes!!";
    }
}

