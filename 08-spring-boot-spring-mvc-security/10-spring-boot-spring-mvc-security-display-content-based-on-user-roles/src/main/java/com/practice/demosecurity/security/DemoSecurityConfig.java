package com.practice.demosecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {
    // Since we define our users here, Spring will now not use the default username and password
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
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

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers("/").hasRole("EMPLOYEE") // this means that any user can have access to root '/' path as all of our users have 'EMPLOYEE' as their role.
                                .requestMatchers("/leaders/**").hasRole("MANAGER") // this means that only user with role as 'MANAGER' can have access to '/leaders' and its routes.
                                .requestMatchers("/systems/**").hasRole("ADMIN")   // this means that only user with role as 'ADMIN' can have access to '/systems' and its routes.
                                .anyRequest().authenticated()
                )
                .exceptionHandling(configurer ->
                        configurer
                                .accessDeniedPage("/access-denied"))
                .formLogin(form ->
                        form
                                .loginPage("/showMyLoginPage")
                                .loginProcessingUrl("/authenticateTheUser")
                                .permitAll()
                )
                .logout(logout -> logout.permitAll()
                );
        return http.build();
    }
}

/*
-- configurer.anyRequest().authenticated()
   This line means that any request coming to this application must be authenticated.
   The user must log in to enter the home page.

-- .formLogin(form ->
   This line means that we are letting spring know that we are going to use a custom login page.

-- form.loginPage("/showMyLoginPage")
   This line means that show our custom form at the request mapping "/showMyLoginPage".
   So we need to create a controller to write the code for request mapping for this URL

-- .loginProcessingUrl("/authenticateTheUser")
   This line means that once the user enters username/password and clicks 'login' button. The spring will send that data
   to this URL "/authenticateTheUser" where the Spring Security will check the username and password.
   No controller request mapping is required for this URL, we kind of get this from spring automatically.
   this URL must not change for spring to check data automatically

-- .permitAll()
   This means that we are allowing anyone to see the login page, there is no restrictions on anyone to see the login page.

-- .logout(logout -> logout.permitAll()
   This adds the logout support for users by sending data to a default URL /logout.
   Logout URL will be handled by Spring Security Filters.
   No controller mapping is required for this URL, we kind of get this from spring automatically.
*/
