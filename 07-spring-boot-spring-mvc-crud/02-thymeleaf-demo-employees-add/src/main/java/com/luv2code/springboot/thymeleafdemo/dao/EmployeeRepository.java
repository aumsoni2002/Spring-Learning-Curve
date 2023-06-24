package com.luv2code.springboot.thymeleafdemo.dao;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	// that's it ... no need to write any code LOL!

    // By putting the method's name exactly as shown below, The Spring Data JPA will parse the method name
    // into a query and provide the working method auto-magically.
    public List<Employee> findAllByOrderByLastNameAsc(); // " From Employee order by lastName asc "

}
