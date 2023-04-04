package com.practice.util;

import org.springframework.stereotype.Component;
@Component
public class CricketCoach implements Coach{
    @Override
    public String getDailyWorkout() {
        return "Practice Fast Bowling for 15 Minutes!!";
    }
}

/*
As these 'Coach.java' and 'CricketCoach.java' file are not inside the 'springcoredemo' package which has the Main Spring
Boot Application, and they are inside the 'util' package which is outside of it.  Spring Boot will not component scan
these files.
*/