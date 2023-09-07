package com.practice.cruddemo;

import com.practice.cruddemo.dao.AppDAO;
import com.practice.cruddemo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

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

            // findInstructorWithCourses(appDAO);

            // findCoursesForInstructor(appDAO);

            // findInstructorWithCoursesJoinFetch(appDAO);

            // updateInstructor(appDAO);

            // updateCourse(appDAO);

            // deleteInstructorWithoutCourses(appDAO);

            // deleteCourseById(appDAO);

            // createCourseAndReviews(appDAO);

            // retrieveCourseAndReviews(appDAO);

            // deleteCourseAndReviews(appDAO);

            // createCourseAndStudents(appDAO);

            // findCourseAndStudents(appDAO);

            findStudentAndCourses(appDAO);
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
        Instructor tempInstructor = new Instructor("Aum", "Soni", "aumsoni2002@gmail.com");

        // creating a new instructor detail
        InstructorDetail tempInstructorDetail = new InstructorDetail("URL3", "Playing Basketball");

        // setting the new instructor detail object to our tempInstructor object
        tempInstructor.setInstructorDetail(tempInstructorDetail);

        // create some courses
        Course tempCourse1 = new Course("Intro to Python");
        Course tempCourse2 = new Course("Java Intermediate");

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

    private void findInstructorWithCourses(AppDAO appDAO) {
        int theId = 1;
        System.out.println("Finding instructor with id: " + theId);
        Instructor tempInstructor = appDAO.findInstructorById(theId);
        System.out.println("tempInstructor: " + tempInstructor);
        System.out.println("the associated courses: " + tempInstructor.getCourses());
    }

    public void findCoursesForInstructor(AppDAO appDAO) {
        int theId = 1;
        System.out.println("Finding instructor with id: " + theId);
        Instructor tempInstructor = appDAO.findInstructorById(theId);
        System.out.println("tempInstructor: " + tempInstructor);

        // As we are using Fetch Type Lazy, we need to find courses for instructor
        System.out.println("Finding courses for instructor with id: " + theId);

        // finding courses of the same above tempInstructor that we found
        List<Course> courses = appDAO.findCoursesByInstructorId(theId);

        // we need to set the courses (associate the object) that we found for our tempInstructor
        tempInstructor.setCourses(courses);

        System.out.println("the associated courses: " + tempInstructor.getCourses());
    }

    public void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
        int theId = 1;
        System.out.println("Finding instructor with id: " + theId);
        Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);
        System.out.println("tempInstructor: " + tempInstructor);
        System.out.println("the associated courses: " + tempInstructor.getCourses());
    }

    public void updateInstructor(AppDAO appDAO) {
        int theId = 1;
        System.out.println("Finding instructor with id: " + theId);
        Instructor tempInstructor = appDAO.findInstructorById(theId);

        System.out.println("Updating instructor's last name to 'Tester'");
        tempInstructor.setLastName("Tester");
        appDAO.update(tempInstructor);
    }

    public void updateCourse(AppDAO appDAO) {
        // finding the course with its own id
        int theId = 10;
        System.out.println("Finding course with id: " + theId);
        Course tempCourse = appDAO.findCourseById(theId);

        // updating the course with its own id.
        System.out.println("Updating course's title to 'C++ Programming'");
        tempCourse.setTitle("C++ Programming");
        appDAO.update(tempCourse);
    }

    public void deleteInstructorWithoutCourses(AppDAO appDAO) {
        int theId = 1;
        System.out.println("deleting instructor with id: " + theId);
        appDAO.deleteInstructorWithoutCoursesById(theId);
    }

    public void deleteCourseById(AppDAO appDAO) {
        int theId = 11;
        System.out.println("deleting course with id: " + theId);
        appDAO.deleteCourseById(theId);
    }

    public void createCourseAndReviews(AppDAO appDAO) {
        // create a course
        Course tempCourse = new Course("Object Oriented Programming with C++");

        // add reviews to course
        tempCourse.addReview(new Review("The course is bad!"));
        tempCourse.addReview(new Review("worst Course!!!!!"));
        tempCourse.addReview(new Review("Redundant Content!!!!!!!!!!!"));

        // save the course to database
        appDAO.save(tempCourse);
    }

    public void retrieveCourseAndReviews(AppDAO appDAO) {
        // get the course and reviews
        int theId = 10;
        Course tempCourse = appDAO.findCourseAndReviewsByCourseId(theId);

        // print the course
        System.out.println(tempCourse);

        // print the reviews
        System.out.println(tempCourse.getReviews());
    }

    public void deleteCourseAndReviews(AppDAO appDAO) {
        int theId = 10;
        System.out.println("Deleting course with id: " + theId);
        appDAO.deleteCourseById(theId);
    }

    public void createCourseAndStudents(AppDAO appDAO) {
        // create a course
        Course tempCourse = new Course("Master Guitar Lessons with Shivang");

        // create a couple of students
        Student tempStudent1 = new Student("Meet", "Soni", "meetsoni123@gmail.com");
        Student tempStudent2 = new Student("Dev", "Vyas", "devvyasi123@gmail.com");

        // add students to our created tempCourse
        tempCourse.addStudent(tempStudent1);
        tempCourse.addStudent(tempStudent2);

        // save the course and its associated student
        System.out.println("Saving the course: " + tempCourse);
        System.out.println("associated students: " + tempCourse.getStudents());

        appDAO.save(tempCourse);
    }

    public void findCourseAndStudents(AppDAO appDAO) {
        // get the course and students
        int theId = 10;
        Course tempCourse = appDAO.findCourseAndStudentsByCourseId(theId);

        // print the course
        System.out.println(tempCourse);

        // print the students
        System.out.println(tempCourse.getStudents());
    }

    public void findStudentAndCourses(AppDAO appDAO){
        // get the student and courses
        int theId = 1;
        Student tempStudent = appDAO.findStudentAndCoursesByStudentId(theId);

        // print the student
        System.out.println(tempStudent);

        // print the courses
        System.out.println(tempStudent.getCourses());
    }
}

/*
Keep one important thing in mind, the instructor detail is going to be saved first and then the instructor will be saved.
That is because due to foreign key relationship the instructor needs to know the id of the instructor detail
*/