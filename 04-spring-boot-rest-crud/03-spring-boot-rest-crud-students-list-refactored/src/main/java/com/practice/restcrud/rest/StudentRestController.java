package com.practice.restcrud.rest;

import com.practice.restcrud.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
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
        theStudents.add(new Student("Poornima", "Patel"));
        theStudents.add(new Student("Mario", "Rossi"));
        theStudents.add(new Student("Mary", "Smith"));
    }

    // define endpoint for "/students" - return a list of students
    @GetMapping("/students")
    public List<Student> getStudents() {
        return theStudents;
    }
}

/* Now there is one issue here, we are creating an ArrayList 'theStudents' and adding new students at every request.
   Which is not efficient and can cause problems if having big applications.

   To resolve this, We have made use of @PostConstruct annotation:
   The @PostConstruct annotation is used to annotate the loadData() method, which initializes the theStudents list with some student data.
   This method is called once after the bean is initialized and all its dependencies are injected.

   When the server starts, the loadData() method is called once and initializes the theStudents list with some initial data. Then,
   when a GET request is made to /api/students, the getStudents() method is invoked, which simply returns the list of students stored in
   theStudents field.
*/