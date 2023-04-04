package com.practice.springcoredemo.config;

import com.practice.springcoredemo.common.Coach;
import com.practice.springcoredemo.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  // With this annotation, we are declaring 'SportConfig' a configuration class for configuring spring.
public class SportConfig {

    //    @Bean("aquatic")            // we can also create our custom bean id and use it inside Qualifier("aquatic")
    @Bean
    public Coach swimCoach() {  // The bean id defaults to the method name, So this bean will have a bean id of 'swimCoach'.
        return new SwimCoach();	// returning a new instance of SwimCoach. Here we are manually constructing the object and returning to given caller.
    }
}
