/*
 * Copyright (c) 2019 Thermo Fisher Scientific
 * All rights reserved.
 */


package personal.springboot.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import personal.springboot.example.entity.Employee;


/**
 * TODO: Class description
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>
{
    /**
     * TODO: Method description
     *
     *
     *
     * @param email
     *
     * @return
     */
    @Query("SELECT e FROM Employee e WHERE e.email LIKE %:email% AND e.active = true")
    List<Employee> findByEmailAndActived(@Param("email") String email);
}

