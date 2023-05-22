package com.luv2code.springboot.cruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) { // here we are injecting DataSource, so that JDBC Authentication can make use of it.

        JdbcUserDetailsManager jdbcUserDetailManager = new JdbcUserDetailsManager(dataSource);

        jdbcUserDetailManager
                .setUsersByUsernameQuery("select user_id, pw, active from members where user_id=?");

        jdbcUserDetailManager
                .setAuthoritiesByUsernameQuery("select user_id, role from roles where user_id=?");

        return jdbcUserDetailManager;  // here we are telling spring to make use of JDBC Authentication with our datasource.
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->    // authorizing http request with this line of code
                configurer
                        .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN"));

        // Letting Spring know that we are using HTTP Basic authentication
        http.httpBasic();

        // disable Cross Site Request Forgery
        http.csrf().disable();

        return http.build();
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

/*
-- SecurityFilterChain
   In Spring Security, the SecurityFilterChain represents a chain of filters that are responsible for processing security-related tasks,
   such as authentication and authorization, for incoming HTTP requests. It is a crucial component in the Spring Security filter-based
   architecture.

   The SecurityFilterChain interface is implemented by the FilterChainProxy class, which acts as a central point for managing and executing
   multiple filter chains within a Spring Security-enabled application. Each SecurityFilterChain is associated with a specific set of URLs
   or patterns and defines the security configuration to be applied to those URLs.
*/

/*
-- http.httpBasic()
   In Spring Security, the httpBasic() method is used within the SecurityFilterChain to configure HTTP Basic authentication.
   HTTP Basic authentication is a simple authentication scheme where the client includes a username and password in the "Authorization"
   header of the HTTP request.

   The httpBasic() method is then called to enable HTTP Basic authentication. By invoking this method, Spring Security adds a
   BasicAuthenticationFilter to the SecurityFilterChain. This filter is responsible for processing the incoming requests, extracting
   the username and password from the "Authorization" header, and authenticating the user against the configured user store
   (e.g., UserDetailsService or AuthenticationProvider).

   HTTP Basic authentication is a widely supported authentication scheme, but it has some limitations. The username and password are
   sent with each request, which may expose them to interception if not using a secure connection (HTTPS). Additionally, as the credentials
   are sent with each request, there is no built-in mechanism for session management or logout.

   It's important to note that the usage of httpBasic() within a SecurityFilterChain is just one configuration option in Spring Security.
   There are several other authentication mechanisms and additional methods available to customize the security behavior according to your
   application's specific requirements.
*/
