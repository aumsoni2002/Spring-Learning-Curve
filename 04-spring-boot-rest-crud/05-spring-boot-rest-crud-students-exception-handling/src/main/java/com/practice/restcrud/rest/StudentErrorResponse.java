package com.practice.restcrud.rest;

public class StudentErrorResponse {

    // Private Fields/Variables
    private int status;
    private String message;
    private long timestamp;

    // Constructor
    public StudentErrorResponse(){

    }

    public StudentErrorResponse(int status, String message, long timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }


    // Getters
    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    // Setters
    public void setStatus(int status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}

/*
This is a Custom Error Response POJO Class which has 3 fields
status, message and timeStamp.

We will make use of this class my setting values to above 3 fields and showing them to user in the form of JSON String
*/