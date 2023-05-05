package com.practice.restcrud.rest;

import com.practice.restcrud.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private List<Student> theStudents;  // here we have created a private field 'theStudents' of type 'Student'.

    // The @PostConstruct annotation is used to annotate the loadData() method, which initializes the theStudents list with some student data.
    // This method is called once after the bean is initialized and all its dependencies are injected.
    @PostConstruct
    public void loadData() {
        theStudents = new ArrayList<>();
        theStudents.add(new Student("Poornima", "Patel"));  // http://localhost:8080/api/students/0
        theStudents.add(new Student("Mario", "Rossi"));     // http://localhost:8080/api/students/1
        theStudents.add(new Student("Mary", "Smith"));      // http://localhost:8080/api/students/2
    }

    // define endpoint for "/students" - return a list of students
    @GetMapping("/students")
    public List<Student> getStudents() {
        return theStudents;
    }


    // define endpoint for "/students/{studentId}" return student at index
    @GetMapping("/students/{studentId}")
    // here it is necessary to mention the path variable name in curly braces to let spring know that, it will vary.
    public Student getStudent(@PathVariable int studentId) { // Now this annotation is letting spring know that the name 'studentId' is going to be use as index value and must be same as above name

        // check the studentId against the length of array
        if (studentId >= theStudents.size() || (studentId < 0)) {
            // here it throws an error which calls the StudentNotFoundException's constructor
            throw new StudentNotFoundException("Student with id " + studentId + " is not found.");
        }

        return theStudents.get(studentId);
    }

}

