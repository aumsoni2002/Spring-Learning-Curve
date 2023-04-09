package com.practice.cruddemo;

import com.practice.cruddemo.dao.StudentDAO;
import com.practice.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CruddemoApplication.class, args);
    }

    // The below snippet of code will be executed after the spring beans have been loaded.
    @Bean
    public CommandLineRunner commandlineRunner(StudentDAO studentDAO) { // here we are injecting an object 'studentDAO' of type 'StudentDAO' to use this interface's methods
        return runner -> {
            // createStudent(studentDAO);        // here we are sending the same object to the method 'createStudent' as an argument.
            // readStudent(studentDAO);
            // queryAllStudents(studentDAO);
            // queryStudentByLastName(studentDAO);
            // updateStudent(studentDAO);
            deleteStudent(studentDAO);
        };
    }

    private void createStudent(StudentDAO studentDAO) {
        // create the student object
        System.out.println("Creating new student object...");
        Student newStudent = new Student("aum", "soni", "aumsoni2002@gmail.com");

        // save the student object
        System.out.println("Saving student object...");
        studentDAO.save(newStudent);

        // display id of the saved student
        System.out.println("The student is saved. The generated ID is: " + newStudent.getId());
    }

    private void readStudent(StudentDAO studentDAO) {
        // create the student object
        System.out.println("Creating new student object...");
        Student secondStudent = new Student("shivang", "soni", "shivangsoni1998@gmail.com");

        // save the student object
        System.out.println("Saving student object...");
        studentDAO.save(secondStudent);

        // display id of the saved student
        System.out.println("The student is saved. The generated ID is: " + secondStudent.getId());

        // Now that a new student will get created with above steps lets retrieve that student and print it in terminal

        // Retrieve student based on its id
        System.out.println("Retrieving student with id: " + secondStudent.getId());

        // here we are calling the 'findById' method of 'StudentDAO' interface which will find the student object from database table and return it
        Student readSecondStudent = studentDAO.findById(secondStudent.getId());

        // Printing the returned student Object
        System.out.println("Found the student: " + readSecondStudent);
    }

    private void queryAllStudents(StudentDAO studentDAO) {
        // here we are calling 'findAll' method of 'StudentDAO' interface which will find all the student objects from database table and return it
        List<Student> allStudents = studentDAO.findAll();

        // Printing all student objects through enhanced for loop
        for (Student eachStudent : allStudents) {
            System.out.println(eachStudent);
        }
    }

    private void queryStudentByLastName(StudentDAO studentDAO) {
        // here we are calling 'findByLastName' method of 'StudentDAO' interface which will find all the student objects whose
        // last name is 'soni' from database table and return it
        List<Student> allStudents = studentDAO.findByLastName("soni");

        // Printing all student objects through enhanced for loop
        for (Student eachStudent : allStudents) {
            System.out.println(eachStudent);
        }
    }

    private void updateStudent(StudentDAO studentDAO) {
        int studentId = 1;      // we are going to change firstName and email whose id is 1.
        System.out.println("Getting student with id: " + studentId);
        System.out.println("Updating the student...");

        Student toBeupdatedStudent = studentDAO.findById(studentId); // finding a student object with id equals to 1.
        toBeupdatedStudent.setFirstName("amit");    // change first name to "amit"
        toBeupdatedStudent.setEmail("amitsoni1971@gmail.com");  // change email to "amitsoni1971@gmail.com"

        studentDAO.update(toBeupdatedStudent);  // updating new changes into our database.
        System.out.println(toBeupdatedStudent);
    }

    private void deleteStudent(StudentDAO studentDAO){
        int studentId = 1;
        System.out.println("deleting student with id: " + studentId);
        studentDAO.delete(studentId);
    }
}

/*
• JPA Dev Process - To Do List
1. Annotate Java Class
2. Develop Java Code to perform database operations

• Terminology
Entity Class: It is just a Java Class that is mapped to a database table.

• Object-To-Relational Mapping (ORM)
We have our Java class called 'Student' that has four fields, id, firstName, lastName, and email, we'll make use of JPA,
and we'll map it to a given database table called 'Student' that has four columns, id, first_name, last_name, and email.
So we need to kind of provide mappings between the two.

As we know, Hibernate is the default JPA implementation in Spring Boot.
Example:
Java Class		-->	HIBERNATE/JPA		-->	Database Table
Student			<--	(Middleware)		<--	Student
id: int								id INT
firstName: String						first_name VARCHAR(45)
lastName: String						last_name VARCHAR(45)
email: String							email VARCHAR(45)

• Entity Class
At a minimum, the Entity class:
Must be annotated with @Entity
Must have a public or protected no-argument constructor. The class can also have other constructors.

• Java Annotations
Step 1: Map class to database table
@Entity					// here we are declaring our 'Student' java class as an Entity Class.
@Table(name="student")			// here we are mapping our java class to the database table 'student'. (name="tableName")
public class Student{
    ...
}

Step 2: Map fields to database columns
@Entity
@Table(name="student")
public class Student{
    @id
    @Column(name="id")			// here we are mapping our 'id' field of java class to the 'id' column of the Database table.
    private int id;

    @Column(name="first_name")		// here we are mapping our 'firstName' field of java class to the 'first_name' column of the Database table.
    private String firstName;
}

• @Column and @Table - Optional
the use of @Column is optional.
If you do not want to specify @Column then the column name must be same name as Java field.
Same goes with the @Table too.

• Terminology
Primary Key: Uniquely identifies each row in a table. Must be a unique value. Cannot contain NULL values

• MySQL - Auto Increment
In the MySQL database, we can make use of an auto increment. We can define our primary key, and then we can specify that it's an auto increment.
So In below code, we have a little snippet here, create table student.
We have this column id which of type int, we declare here that it will never be null, and then we specify auto increment.
Behind the scenes, MySQL will keep track by automatically incrementing this id, and making sure it's a unique value.
We also specify that our given column id is the primary key for this given table.
MySQL Code:
CREATE TABLE student {
  id int NOT NULL AUTO_INCREMENT,			// here we are declaring that id cannot be null, and we specify auto increment on it.
  first_name varchar(45) DEFAULT NULL,
  last_name varchar(45) DEFAULT NULL,
  email varchar(45) DEFAULT NULL,
  PRIMARY KEY (id)					// here we are declaring our 'id' column as the primary key of this table.
}

• JPA Identity - Primary Key
Once we specify auto increment into our database table, we also need to declare it into our Java Code with @Id annotation
@Entity
@Table(name="student")
public class Student{
    @Id							// here we are declaring our 'id' field as the Unique Identifier of this Entity Class
    @GeneratedValue(strategy=GenerationType.IDENTITY)	// here what we are saying is that this id value will be generated by the database and managed by the database.
							// There's no need for our code to manually try and keep track of that given id.
    @Column(name="id")					// here we are mapping our 'id' field of java class to the 'id' column of the Database table.
    private int id;
}

• ID Generation Strategies
Name				Description
GenerationType.AUTO		Pick an appropriate strategy for the particular database
GenerationType.IDENTITY		Assign primary keys using database identity column
GenerationType.SEQUENCE		Assign primary keys using a database sequence
GenerationType.TABLE		Assign primary keys using an underlying database table to ensure uniqueness

• Custom generation strategy
We can define your own CUSTOM generation strategy :-)
Create implementation of org. hibernate.id.IdentifierGenerator
Override the method: public Serializable generate(...)

*/