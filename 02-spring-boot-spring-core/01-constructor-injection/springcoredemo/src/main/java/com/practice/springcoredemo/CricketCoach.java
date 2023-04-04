package com.practice.springcoredemo;

import org.springframework.stereotype.Component;
// @Component marks the class as a Spring Bean
// A Spring Bean is just a regular Java class that is managed by Spring
// @Component also makes the bean available for dependency injection
@Component
public class CricketCoach implements Coach{
    @Override
    public String getDailyWorkout() {
        return "Practice Fast Bowling for 15 Minutes!!";
    }
}
