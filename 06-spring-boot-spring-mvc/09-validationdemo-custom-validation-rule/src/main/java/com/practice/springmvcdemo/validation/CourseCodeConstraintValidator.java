package com.practice.springmvcdemo.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String> { // CourseCode is our annotation and String is the type of data that we are validating against
    private String coursePrefix;

    @Override
    public void initialize(CourseCode theCourseCode) {
        coursePrefix = theCourseCode.value();    // here we are setting the actual default value so that we can use it to validate against the data that user enters
    }

    @Override
    public boolean isValid(String theCode, ConstraintValidatorContext theConstraintValidatorContext) { // here theCode is the data that is entered by user, ConstraintValidatorContext is just an helper class that is added for additional error messages
        boolean result;
        if (theCode != null) { // if theCode is not null, it will go in this if statement.
            result = theCode.startsWith(coursePrefix); // here it checks that if theCode starts with the coursePrefix that is 'AUM', it will return true or it will return false.
        } else {
            result = true; // if theCode is null, it will return false as the field Course Code is not required.
        }
        return result;
    }
}