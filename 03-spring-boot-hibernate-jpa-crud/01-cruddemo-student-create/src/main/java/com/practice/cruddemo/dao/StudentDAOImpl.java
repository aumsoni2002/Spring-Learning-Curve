package com.practice.cruddemo.dao;

import com.practice.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class StudentDAOImpl implements StudentDAO {// creating a class 'StudentDAOImpl' which implements 'StudentDAO' interface to use the method 'save'.
    private EntityManager entityManager; // creating a field 'entityManager' of type 'EntityManager' which will be assigned an instance of type 'EntityManager' through constructor

    // inject entity manager using constructor injection
    @Autowired
    public StudentDAOImpl(EntityManager theEntityManager) {    // here we have created a constructor which take an object(instance) of type 'EntityManager' as an argument
        entityManager = theEntityManager;        // here we are saving the coming new instance to the field 'entityManager'
    }

    @Override
    @Transactional                    // Handles Transaction Management which is done inside the 'save' method
    public void save(Student theStudent) {
        entityManager.persist(theStudent);  // here we are overriding the 'save' method with saving the new Student object into our database table.
    }
}
