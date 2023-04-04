package com.practice.springcoredemo.common;

import org.springframework.stereotype.Component;

//@Lazy                        // this will implement Lazy Initialization to this class.
@Component
public class CricketCoach implements Coach {
    public CricketCoach() {
        System.out.println("In constructor: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Practice Fast Bowling for 15 Minutes!!";
    }
}

