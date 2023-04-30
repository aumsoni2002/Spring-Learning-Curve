package com.practice.restcrud.rest;

import com.practice.restcrud.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /*
    Below class handles the exception by returning the status, message and timestamp in JSON Formatted String. It instantiates a new
    object 'error' of type StudentErrorResponse and sets value for 3 fields: status, message and timestamp
    */
    @ExceptionHandler    // This annotation is used to let spring know that below method is used in Exception handling
    // this exception handling method is of response type body 'StudentErrorResponse'
    // And it is of exception type 'StudentNotFoundException' to handle and catch the error.
    // That means it says that this below function or exception handler can handle or catch 'StudentNotFoundException' type errors.
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {
        StudentErrorResponse error = new StudentErrorResponse(HttpStatus.NOT_FOUND.value(), exc.getMessage(), System.currentTimeMillis());
//        error.setStatus(HttpStatus.NOT_FOUND.value());
//        error.setMessage(exc.getMessage());
//        error.setTimestamp(System.currentTimeMillis());

        // Here Jackson will convert the error(body) into JSON Format and return it. And we are also sending actual status code as response.
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Above Exception Handler only works when an integer is written as studentId, but it will not work if we enter characters and letters.

    // Below Exception Handler will catch any exception(all generic exceptions) returns 'Bad Request' as status code, if characters and letters are entered.
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exc) { // here 'Exception' is for handling all generic exceptions
        StudentErrorResponse error = new StudentErrorResponse(HttpStatus.BAD_REQUEST.value(), exc.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}

