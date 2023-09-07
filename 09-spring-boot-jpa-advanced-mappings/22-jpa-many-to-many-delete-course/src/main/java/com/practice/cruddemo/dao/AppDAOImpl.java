package com.practice.cruddemo.dao;

import com.practice.cruddemo.entity.Course;
import com.practice.cruddemo.entity.Instructor;
import com.practice.cruddemo.entity.InstructorDetail;
import com.practice.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    // As we are performing save operation i.e. we are saving a new instructor into the database, we need this annotation
    public void save(Instructor theInstructor) {
        // here we are saving the new instructor object, and it will also save the instructor detail object.
        entityManager.persist(theInstructor);
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

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        // here we are retrieving the instructorDetail object and it will also retrieve the associated instructor object
        return entityManager.find(InstructorDetail.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {
        // retrieve instructor detail
        InstructorDetail tempInstructorDetail = entityManager.find(InstructorDetail.class, theId);

        // remove the associated object reference, so it breaks the bidirectional link
        tempInstructorDetail.getInstructor().setInstructorDetail(null);

        // remove instructor detail
        entityManager.remove(tempInstructorDetail); // here we are removing the instructor detail which will also remove the associated instructor
    }

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {
        // create query to retrieve courses from database
        TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id = :data", Course.class);
        query.setParameter("data", theId);

        // execute query
        List<Course> courses = query.getResultList();

        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {
        // create query to retrieve instructor and courses from database
        TypedQuery<Instructor> query = entityManager.createQuery("select i from Instructor i " + "JOIN Fetch i.courses " + "JOIN Fetch i.instructorDetail " + "where i.id = :data", Instructor.class);
        query.setParameter("data", theId);

        // execute query
        Instructor instructor = query.getSingleResult();

        return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor tempInstructor) {
        entityManager.merge(tempInstructor);
    }

    @Override
    public Course findCourseById(int theId) {
        // this will find the course by its own id.[Note: it will not find it by instructor's id as we did in above one of the section]
        return entityManager.find(Course.class, theId);
    }

    @Override
    @Transactional
    public void update(Course tempCourse) {
        entityManager.merge(tempCourse);
    }

    @Override
    @Transactional
    public void deleteInstructorWithoutCoursesById(int theId) {
        // finding the instructor
        Instructor tempInstructor = entityManager.find(Instructor.class, theId);

        // retrieving all courses that are associated to this tempInstructor
        List<Course> courses = tempInstructor.getCourses();

        // Now that we have courses, we need to set the instructor of all these courses to null to break the association,
        // or we will not be able to remove the instructor
        for (Course tempCourse : courses) {
            tempCourse.setInstructor(null);
        }

        // Now that we have break the association, we can remove the instructor from the database
        entityManager.remove(tempInstructor);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {
        // retrieving the course from database
        Course tempCourse = entityManager.find(Course.class, theId);

        // removing the course from database
        entityManager.remove(tempCourse);
    }

    @Override
    @Transactional
    public void save(Course theCourse) {
        // save the course and its associated reviews
        entityManager.persist(theCourse);    // this will save the course and its associated reviews because we have put cascase type 'ALL'
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int theId) {
        // create a query to find course and its reviews
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c " + "JOIN FETCH c.reviews " + "where c.id = :data", Course.class);
        query.setParameter("data", theId);

        // execute query
        Course course = query.getSingleResult();

        return course;
    }

    @Override
    public Course findCourseAndStudentsByCourseId(int theId) {
        // create a query to find course and its students
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c " + "JOIN FETCH c.students " + "where c.id = :data", Course.class);
        query.setParameter("data", theId);

        // execute query
        Course course = query.getSingleResult();

        return course;
    }

    @Override
    public Student findStudentAndCoursesByStudentId(int theId) {
        // create a query to find student and its courses
        TypedQuery<Student> query = entityManager.createQuery("select s from Student s " + "JOIN FETCH s.courses " + "where s.id = :data", Student.class);
        query.setParameter("data", theId);

        // execute query
        Student student = query.getSingleResult();

        return student;
    }

    @Override
    @Transactional
    public void update(Student tempStudent) {
        entityManager.merge(tempStudent);
    }

    @Override
    @Transactional
    public void deleteStudentById(int theId) {
        // retrieving the student from database
        Student tempStudent = entityManager.find(Student.class, theId);

        // removing the student from database
        entityManager.remove(tempStudent);
    }
}
