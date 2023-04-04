package com.practice.springcoredemo.common;


// As you can notice here we are not using '@Component' to initialize it as a bean. So check config/SportConfig file.
public class SwimCoach implements Coach{
    public SwimCoach() {
        System.out.println("In constructor: " + getClass().getSimpleName());
    }
    @Override
    public String getDailyWorkout() {
        return "Swim 1000 meters for Warm-up";
    }
}
