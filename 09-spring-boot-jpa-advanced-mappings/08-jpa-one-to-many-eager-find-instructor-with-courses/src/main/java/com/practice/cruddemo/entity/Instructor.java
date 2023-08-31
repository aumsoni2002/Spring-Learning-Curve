package com.practice.cruddemo.entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instructor")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    // This is how we can map our Instructor class to InstructorDetail class
    // here we are letting spring know that there is going to be one-to-one relationship between this class and some other class
    // here we are saying that all operations that we apply to 'instructor' entity, should also be applied to 'instructor_detail' entity
    // Configure Multiple Cascade Types: @OneToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    @OneToOne(cascade = CascadeType.ALL) // It is important to know that by default,  no operation is cascaded.
    @JoinColumn(name = "instructor_detail_id")
    // here we are telling spring to use 'instructor_detail_id' column as foreign key and link it to the primary key of below-mentioned class
    private InstructorDetail instructorDetail; // it is mandatory to mention the class name as a type so that the Hibernate can automatically link both classes.

    // this annotation means that there can only be one instructor mapped to many courses
    // this refers to 'instructor' property in Course class
    // The default Fetch Type for OneToMany is Lazy, so when we fetch and show instructor and courses,
    // it will show error because courses were not fetched as the fetch type was Lazy.
    // The quick solution for it is to change the fetch type to EAGER
    @OneToMany(fetch = FetchType.EAGER,
            mappedBy = "instructor",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
    )
    private List<Course> courses;

    // constructors
    public Instructor() {
    }

    public Instructor(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // getters
    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public InstructorDetail getInstructorDetail() {
        return instructorDetail;
    }

    public List<Course> getCourses() {
        return courses;
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setInstructorDetail(InstructorDetail instructorDetail) {
        this.instructorDetail = instructorDetail;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    // toString() method
    @Override
    public String toString() {
        return "Instructor{" + "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", email='" + email + '\'' + ", instructorDetail=" + instructorDetail + '}';
    }

    // add convenience methods for bidirectional relationship
    public void add(Course tempCourse) {
        if (courses == null) {
            courses = new ArrayList<>();
        }

        courses.add(tempCourse);

        // this will basically set up the two-way link between the instructor and Course so that we can have access to them through both ways
        tempCourse.setInstructor(this);
    }
}
