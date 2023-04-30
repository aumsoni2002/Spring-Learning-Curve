package com.practice.restcrud.rest;

// Here we are making use of inheritance, so that we can make use of fields and methods of RuntimeException class
public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String message) {
        // this will call the constructor of the super class which is the RuntimeException class, and we are sending the 'message' as argument.
        super(message);
    }

    public StudentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public StudentNotFoundException(Throwable cause) {
        super(cause);
    }
}

/*
This is Custom Exception Class which will be called once a new error has been thrown by the StudentRestController class.
*/