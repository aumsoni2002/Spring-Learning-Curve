package com.practice.cruddemo.dao;

import com.practice.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

// creating a class 'EmployeeDAOJpaImpl' which implements 'StudentDAO' interface to use the methods such as 'findAll' and many others.
@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO{

    // creating a field 'entityManager' of type 'EntityManager' which will be assigned an instance of type 'EntityManager'
    // through constructor
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    @Autowired
    public EmployeeDAOJpaImpl(EntityManager theEntityManager) {    // here we have created a constructor which take an object(instance) of type 'EntityManager' as an argument
        entityManager = theEntityManager;        // here we are saving the coming new instance to the field 'entityManager'
    }

    @Override
    public List<Employee> findAll() {
        // create a query
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);

        // execute query and get result list
        List<Employee> employees = theQuery.getResultList();

        // return the results
        return employees;
    }
}


/*
In Java Spring Boot, @Repository is a Spring annotation that is used to indicate that a particular class provides data
access and storage functionality, typically to a relational database.

When a class is annotated with @Repository, it is automatically registered as a bean in the Spring application context,
and Spring provides some additional features and capabilities such as exception translation and transaction management.

The @Repository annotation is typically used on classes that implement Spring's Repository interface or its subinterfaces,
which provide a set of methods for CRUD (Create, Read, Update, Delete) operations on the underlying data store. By using
the @Repository annotation, the implementation class can be easily identified and managed by Spring's container.

Overall, @Repository is a convenient way to provide a consistent and structured approach to data access and persistence
in a Spring Boot application.
*/