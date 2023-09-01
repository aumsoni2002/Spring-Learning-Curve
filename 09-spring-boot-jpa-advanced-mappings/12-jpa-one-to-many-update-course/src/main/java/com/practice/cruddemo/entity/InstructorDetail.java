package com.practice.cruddemo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "instructor_detail")
public class InstructorDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "youtube_channel")
    private String youtubeChannel;

    @Column(name = "hobby")
    private String hobby;

    // As we can see in below code, we have put all cascade types other than REMOVE,
    // now when we remove the instructor detail, its associated instructor will not get deleted
    @OneToOne(mappedBy = "instructorDetail", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    // this here refers to 'instructorDetail' property in 'Instructor' class
    private Instructor instructor; // so this means that this instructor property is mapped by the instructorDetail property in 'Instructor' class


    // constructors
    public InstructorDetail() {
    }

    public InstructorDetail(String youtubeChannel, String hobby) {
        this.youtubeChannel = youtubeChannel;
        this.hobby = hobby;
    }


    // Getters
    public int getId() {
        return id;
    }

    public String getYoutubeChannel() {
        return youtubeChannel;
    }

    public String getHobby() {
        return hobby;
    }

    public Instructor getInstructor() {
        return instructor;
    }


    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setYoutubeChannel(String youtubeChannel) {
        this.youtubeChannel = youtubeChannel;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    // toString() method

    @Override
    public String toString() {
        return "InstructorDetail{" + "id=" + id + ", youtubeChannel='" + youtubeChannel + '\'' + ", hobby='" + hobby + '\'' + '}';
    }
}
