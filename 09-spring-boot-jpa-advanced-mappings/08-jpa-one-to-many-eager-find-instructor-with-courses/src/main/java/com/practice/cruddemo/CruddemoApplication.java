package com.practice.cruddemo;

import com.practice.cruddemo.dao.AppDAO;
import com.practice.cruddemo.entity.Course;
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
            // createInstructor(appDAO);

            // findInstructorById(appDAO);

            // deleteInstructorById(appDAO);

            // findInstructorDetailById(appDAO);

            // deleteInstructorDetailById(appDAO);

            // createInstructorWithCourses(appDAO);

            findInstructorWithCourses(appDAO);
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

    private void findInstructorById(AppDAO appDAO) {
        int theId = 1;
        System.out.println("Finding Instructor with id: " + theId);
        Instructor tempInstructor = appDAO.findInstructorById(theId);
        System.out.println("tempInstructor: " + tempInstructor);
        System.out.println("the associated instructor detail: " + tempInstructor.getInstructorDetail());
    }

    private void deleteInstructorById(AppDAO appDAO) {
        int theId = 3;
        System.out.println("Delete Instructor with id: " + theId);
        appDAO.deleteInstructorById(theId);
        System.out.println("Done!");
    }

    private void findInstructorDetailById(AppDAO appDAO) {
        int theId = 1;
        System.out.println("Finding Instructor Detail with id: " + theId);
        InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);
        System.out.println("tempInstructorDetail: " + tempInstructorDetail);
        System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());
    }

    private void deleteInstructorDetailById(AppDAO appDAO) {
        int theId = 4;
        System.out.println("Delete Instructor detail with id: " + theId);
        appDAO.deleteInstructorDetailById(theId);
        System.out.println("Done!");
    }

    public void createInstructorWithCourses(AppDAO appDAO) {
        // creating a new instructor
        Instructor tempInstructor = new Instructor("Shivang", "Soni", "shivngsoni1998@gmail.com");

        // creating a new instructor detail
        InstructorDetail tempInstructorDetail = new InstructorDetail("URL2", "Playing Guitar");

        // setting the new instructor detail object to our tempInstructor object
        tempInstructor.setInstructorDetail(tempInstructorDetail);

        // create some courses
        Course tempCourse1 = new Course("Introduction to Web Development");
        Course tempCourse2 = new Course("Data Structures and Algorithm");

        // add courses to instructor
        tempInstructor.add(tempCourse1);
        tempInstructor.add(tempCourse2);

        // save the instructor into the database
        // NOTE: this will ALSO save the courses because of CascadeType.PERSIST
        System.out.println("Saving instructor: " + tempInstructor);
        System.out.println("The Courses: " + tempInstructor.getCourses());
        appDAO.save(tempInstructor);
        System.out.println("Done!!");
    }

    private void findInstructorWithCourses(AppDAO appDAO){
        int theId = 1;
        System.out.println("Finding instructor with id: " + theId);
        Instructor tempInstructor = appDAO.findInstructorById(theId);
        System.out.println("tempInstructor: " + tempInstructor);
        System.out.println("the associated courses: " + tempInstructor.getCourses());
    }
}

/*
Keep one important thing in mind, the instructor detail is going to be saved first and then the instructor will be saved.
That is because due to foreign key relationship the instructor needs to know the id of the instructor detail
*/