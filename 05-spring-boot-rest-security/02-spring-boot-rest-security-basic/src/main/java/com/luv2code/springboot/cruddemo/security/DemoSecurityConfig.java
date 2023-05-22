package com.luv2code.springboot.cruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class DemoSecurityConfig {
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        /*
        Since we define our users with their passwords here, Spring will not use the username and password
        from the application.properties.
        */

        UserDetails john = User.builder()
                .username("john")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();

        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER")
                .build();

        UserDetails susan = User.builder()
                .username("susan")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(john, mary, susan);
    }
}

/*
-- @Configuration
   In Java, the @Configuration annotation is used in the Spring Framework to mark a class as a source of bean definitions.
   It is typically used in conjunction with other annotations like @Bean, @ComponentScan, and @Import to configure the application
   context and define beans.

   When a class is annotated with @Configuration, it indicates that the class contains bean definitions and should be processed by
   the Spring container to create and manage beans. The @Configuration class is similar to an XML configuration file in Spring, but
   it allows for more flexibility and is often preferred for Java-based configuration.

   By using @Configuration, Spring will process the class and register the defined beans in the application context. Other classes can
   then use these beans by using dependency injection or by retrieving them from the application context.

   Note that in order for the @Configuration class to be processed, it needs to be included in the component scanning or explicitly
   imported into the main Spring configuration.
*/

/*
-- InMemoryUserDetailsManager
   In Spring Security, the InMemoryUserDetailsManager is an implementation of the UserDetailsService interface that
   stores user details in memory. It is a simple and convenient way to define and manage user authentication and authorization
   without the need for an external user database.

   The InMemoryUserDetailsManager allows you to define user credentials, roles, and authorities directly in your application
   configuration, typically in a @Configuration class. It is commonly used for development, testing, or scenarios where a full-fledged
   user database is not required.
*/
