package com.practice.restcrud.rest;

import com.practice.restcrud.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    // define endpoint for "/students" return list of students
    @GetMapping("/students")
    public List<Student> getStudents() {
        List<Student> theStudents = new ArrayList<>();      // here we are creating a new ArrayList of type 'Student'.

        theStudents.add(new Student("Poornima", "Patel"));  // here we are adding new members/elements/students in the above created ArrayList.(Hard Coded)
        theStudents.add(new Student("Mario", "Rossi"));
        theStudents.add(new Student("Mary", "Smith"));

        return theStudents;     // here Jackson will convert List<Student> to JSON Format String and then return it.
    }
}

/* Now there is one issue here, we are creating an ArrayList 'theStudents' and adding new students at every request.
   Which is not efficient and can cause problems if having big applications.

   To resolve this, check project: 03-spring-boot-rest-crud-students-list-refactored
*/