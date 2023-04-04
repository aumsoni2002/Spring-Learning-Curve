package com.practice.springcoredemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {"com.practice.springcoredemo", "com.practice.util"}
)
public class SpringcoredemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcoredemoApplication.class, args);
    }

}

/*
This is the Main Spring Boot Application Class which is created by Spring Initializer: https://start.spring.io/
As you can see above, it imports: org.springframework.boot.autoconfigure.SpringBootApplication
because of which we were able to put the annotation: @SpringBootApplication
Now this annotation enables: Auto configuration, Component scanning, Additional configuration
Behind the Scenes, The @SpringBootApplication composed of @EnableAutoConfiguration, @ComponentScan and @Configuration
annotations.
*/

/*
Here the ' SpringApplication.run ' bootstraps our spring boot application.
And in ' SpringApplication.run(SpringcoredemoApplication.class, args); ', we have to write the actual name of our class to
give a reference.
Behind the Scenes, This both above lines will create the application context, registers all beans and starts the embedded serve.
*/

/*
The Spring Boot will only scan 'springcoredemo' package and its sub-packages because it has the
Main Spring Boot Application Class where we import org.springframework.boot.autoconfigure.SpringBootApplication.
and made use of @SpringBootApplication annotation.

Now to make Spring Boot scan outside packages like 'util' we need to list them as shown in below code:
@SpringBootApplication(
        scanBasePackages = {"com.practice.springcoredemo", "com.practice.util"}
)

As we have to list other packages for component scanning, we also need to list the 'springcoredemo' package.
*/