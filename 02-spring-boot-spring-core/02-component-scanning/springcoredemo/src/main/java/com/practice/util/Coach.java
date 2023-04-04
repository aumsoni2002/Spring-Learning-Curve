package com.practice.util;

public interface Coach {
    String getDailyWorkout();
}

/*
As these 'Coach.java' and 'CricketCoach.java' file are not inside the 'springcoredemo' package which has the Main Spring
Boot Application, and they are inside the 'util' package which is outside of it.  Spring Boot will not component scan
these files.

Now to make Spring Boot scan 'util' package, we need to list it in scanBasePackages code,
inside the Main Spring Boot Application Class.
*/