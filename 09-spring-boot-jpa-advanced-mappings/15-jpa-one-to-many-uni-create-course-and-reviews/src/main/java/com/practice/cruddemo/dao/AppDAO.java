package com.practice.cruddemo.dao;

import com.practice.cruddemo.entity.Course;
import com.practice.cruddemo.entity.Instructor;
import com.practice.cruddemo.entity.InstructorDetail;

import java.util.List;

public interface AppDAO {
    void save(Instructor theInstructor);

    Instructor findInstructorById(int theId);

    void deleteInstructorById(int theId);

    InstructorDetail findInstructorDetailById(int theId);

    void deleteInstructorDetailById(int theId);

    List<Course> findCoursesByInstructorId(int theId);

    Instructor findInstructorByIdJoinFetch(int theId);

    void update(Instructor instructor);

    Course findCourseById(int theId);

    void update(Course course);

    void deleteInstructorWithoutCoursesById(int theId);

    void deleteCourseById(int theId);

    void save(Course theCourse);

}
