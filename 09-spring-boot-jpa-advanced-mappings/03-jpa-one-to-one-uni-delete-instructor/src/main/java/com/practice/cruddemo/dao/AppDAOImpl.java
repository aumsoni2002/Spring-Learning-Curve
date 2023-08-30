package com.practice.cruddemo.dao;

import com.practice.cruddemo.entity.Instructor;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository     // never forgot to annotate a DAO Impl class with this annotation
public class AppDAOImpl implements AppDAO {
    // define field for entity manager
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    // As we are performing save operation i.e. we are saving a new instructor into the the database, we need this annotation
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);        // here we are saving the new instructor object, and it will also save the instructor detail object.
    }

    @Override
    public Instructor findInstructorById(int theId) {
        // now this will also return the associated entity. In our case it will return both instructor and instructor details because by default the fetch type is eager.
        return entityManager.find(Instructor.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        // retrieve the instructor
        Instructor tempInstructor = entityManager.find(Instructor.class, theId);

        // delete the instructor
        entityManager.remove(tempInstructor); // this will also remove the instructor detail object because we have put cascadeType.ALL
    }
}
