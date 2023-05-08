package com.practice.cruddemo.dao;

import com.practice.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// here we are mentioning the Entity type as 'Employee' and Primary Key type as 'Integer' which is necessary.
@RepositoryRestResource(path="members")     // To access: http://localhost:8080/magic-api/members
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // that's it ... no need to write any code LOL! and still we will have access to methods like
    // findAll(), findById(...), save(...), deleteById(...) and many others.
}

/*
JpaRepository is an interface in Spring Data JPA that provides a set of methods for performing common database operations,
such as create, read, update, and delete (CRUD) operations, on entities in a database. It extends the PagingAndSortingRepository
interface and adds additional methods for querying data, such as findBy*, countBy*, and deleteBy* methods.

To use JpaRepository, you need to create a custom interface that extends the JpaRepository interface and specify the entity class
and the type of the entity's primary key as type parameters. For example, suppose you have an entity class called User with a primary
key of type Long. To create a repository for this entity, you would define an interface as follows:

public interface UserRepository extends JpaRepository<User, Long> {
    // additional custom methods can be defined here
}

Once you have defined the repository interface, you can use it to perform CRUD operations on the User entity by invoking the methods
inherited from the JpaRepository interface.
*/