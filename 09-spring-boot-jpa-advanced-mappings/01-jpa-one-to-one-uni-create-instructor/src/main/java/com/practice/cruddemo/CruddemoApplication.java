package com.practice.cruddemo;

import com.practice.cruddemo.dao.AppDAO;
import com.practice.cruddemo.entity.Instructor;
import com.practice.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CruddemoApplication.class, args);
    }

    // This CommandLineRunner is from the spring boot application and below snippet of code will be
    // executed after the Spring Beans have been loaded.
    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {
        return runner -> {
            createInstructor(appDAO);
        };
    }

    private void createInstructor(AppDAO appDAO) {
        // creating a new instructor
        Instructor tempInstructor = new Instructor("Shivang", "Soni", "shivngsoni1998@gmail.com");

        // creating a new instructor detail
        InstructorDetail tempInstructorDetail = new InstructorDetail("URL2", "Playing Guitar");

        // setting the new instructor detail object to our tempInstructor object
        tempInstructor.setInstructorDetail(tempInstructorDetail);

        // saving the new instructor object into our database
        // by saving the instructor, it will also save the instructor detail because of that CascadeType.ALL
        System.out.println("Saving instructor: " + tempInstructor);
        appDAO.save(tempInstructor);
        System.out.println("Done!!");
    }
}

/*
Keep one important thing in mind, the instructor detail is going to be saved first and then the instructor will be saved.
That is because due to foreign key relationship the instructor needs to know the id of the instructor detail
*/