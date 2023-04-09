package com.practice.cruddemo.dao;

import com.practice.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {// creating a class 'StudentDAOImpl' which implements 'StudentDAO' interface to use the method 'save'.
    private EntityManager entityManager; // creating a field 'entityManager' of type 'EntityManager' which will be assigned an instance of type 'EntityManager' through constructor

    // inject entity manager using constructor injection
    @Autowired
    public StudentDAOImpl(EntityManager theEntityManager) {    // here we have created a constructor which take an object(instance) of type 'EntityManager' as an argument
        entityManager = theEntityManager;        // here we are saving the coming new instance to the field 'entityManager'
    }

    @Override
    @Transactional
    // when a method does create, update or delete within the database through a method, That method should have this annotation
    public void save(Student theStudent) {
        entityManager.persist(theStudent);  // here we are overriding the 'save' method with saving the new Student object into our database table.
    }

    @Override
    public Student findById(Integer id) {   // here we are creating a method 'findById' that will take 'id' of type Integer as an argument.
        return entityManager.find(Student.class, id); // here we will send the above 'id' and the 'Student' class as argument to find the data which has the same id.
    }

    @Override
    public List<Student> findAll() {
        // retrieving all student objects from the database table
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student", Student.class);

        // returning the list of all student objects
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByLastName(String theLastName) {
        // retrieving all student objects from the database table whose last name is 'soni'
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student WHERE lastName=:theData", Student.class);

        // Swapping the ':theData' word with what we enter while calling it
        theQuery.setParameter("theData", theLastName);

        // returning the list of all student objects
        return theQuery.getResultList();
    }

    @Override
    @Transactional  // when a method does create, update or delete within the database through a method, That method should have this annotation
    public void update(Student theStudent) {
        entityManager.merge(theStudent);        // here we are updating the existed student object into our database table.
    }


}
