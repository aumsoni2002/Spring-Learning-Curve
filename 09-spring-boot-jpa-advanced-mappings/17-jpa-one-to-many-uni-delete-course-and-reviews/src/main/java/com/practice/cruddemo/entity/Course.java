package com.practice.cruddemo.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    // this annotation means that there can be many courses mapped to one instructor
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "instructor_id")
    // this 'instructor_id' is going to be linked to the 'id' of the instructor class
    private Instructor instructor;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")    // this name here refers to the 'course_id' in the 'review' table
    private List<Review> reviews;

    // constructors
    public Course() {
    }

    public Course(String title) {
        this.title = title;
    }


    // getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    // add convenience methods for adding reviews
    public void addReview(Review tempReview) {
        if (reviews == null) {
            reviews = new ArrayList<>();
        }
        reviews.add(tempReview);
    }

    // toString() method
    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
