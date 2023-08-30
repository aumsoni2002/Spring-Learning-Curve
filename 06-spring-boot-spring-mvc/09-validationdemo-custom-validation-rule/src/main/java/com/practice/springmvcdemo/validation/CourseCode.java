package com.practice.springmvcdemo.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CourseCodeConstraintValidator.class)
// here we have to give it a helper class that contains actual business/validation logic for validating this process
@Target({ElementType.METHOD, ElementType.FIELD})
// here we are saying that this annotation can be used on methods and fields
@Retention(RetentionPolicy.RUNTIME) // process it at runtime
public @interface CourseCode {
    // define default value parameter's value
    // the value() here is to declare that the annotation will have a parameter 'value'.
    // if the value parameter is not used by user while using putting this annotation, the default value will be 'AUM'
    public String value() default "AUM";

    // define default error message
    // the message() here is to declare that the annotation will have a parameter 'message'.
    // if the message parameter is not used by user while using putting this annotation, the default error message will be 'must start with AUM'
    public String message() default "must start with AUM";

    // define default groups
    // groups: it can group related constraints
    public Class<?>[] groups() default {};

    // define default payload
    // Payloads: provide custom details about validation failure (severity level, error code etc)
    public Class<? extends Payload>[] payload () default {};

}
