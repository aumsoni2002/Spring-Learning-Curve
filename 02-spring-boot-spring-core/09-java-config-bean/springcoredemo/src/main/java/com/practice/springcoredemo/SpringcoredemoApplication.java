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
In this is program, we will create a new Class 'SwimCoach' and we will not use any special annotations like '@Component' to configure it as bean.
Instead we will configure it using @Bean annotation.

• Development Process
1. Create @Configuration class
package com. luv2code.springcoredemo.config;
import org.springframework.context.annotation.Configuration;
@configuration								// With this annotation, we are declaring 'SportConfig' a configuration class for configuring spring.
public class SportConfig {
    ...
}

2. Define @Bean method to configure the bean
package com. luv2code.springcoredemo.config;
import com. luv2code.springcoredemo.common.Coach;
import com. luv2code.springcoredemo.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class SportConfig {
    @Bean
    public Coach swimCoach() {		// The bean id defaults to the method name, So this bean will have a bean id of SwimCoach.
        return new SwimCoach();		// returing a new instance of SwimCoach. Here we are manually constructing the object and returning to given caller.
    }
}

3. Inject the bean into our controller
import com.practice.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class DemoController {
    private Coach myCoach;
    @autowired
    public DemoController(@Qualifier("swimCoach") Coach theCoach) {			// here we made use of the bean id 'swimCoach' to inject it.
        System.out.println("In constructor: " + getClass().getSimpleName());
        myCoach = theCoach;
    }
}


• Use case for @Bean
Make an existing third-party class available to Spring framework
You may not have access to the source code of third-party class
However, you would like to use the third-party class as a Spring bean

• Real-World Project Example
Our project used Amazon Web Service (AWS) to store documents
 Amazon Simple Storage Service (Amazon S3)
 Amazon S3 is a cloud-based storage system
 can store PDF documents, images etc
We wanted to use the AWS S3 client as a Spring bean in our app
The AWS S3 client code is part of AWS SDK
 We can’t modify the AWS SDK source code
 We can’t just add @Component
However, we can configure it as a Spring bean using @Bean
*/