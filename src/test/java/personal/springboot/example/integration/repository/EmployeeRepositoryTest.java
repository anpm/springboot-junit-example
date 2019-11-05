/*
 * Copyright (c) 2019 Thermo Fisher Scientific
 * All rights reserved.
 */


package personal.springboot.example.integration.repository;

import java.util.List;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;

import com.github.springtestdbunit.DbUnitTestExecutionListener;

import org.junit.runner.RunWith;

import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import personal.springboot.example.entity.Employee;
import personal.springboot.example.repository.EmployeeRepository;

import static org.junit.Assert.assertEquals;


/**
 * TODO: Class description
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@DirtiesContext

@TestExecutionListeners(
{
    DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class
})
@DatabaseSetup(value = "/dbunit/EmployeeRepositoryTest/startup.xml")
@DatabaseTearDown(value = "/dbunit/EmployeeRepositoryTest/teardown.xml")
public class EmployeeRepositoryTest
{
    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * TODO: Method description
     */
    @Test
    public void testFindByEmailAndActived_FoundTwoRecord_thenSuccess()
    {
        List<Employee> employees = employeeRepository.findByEmailAndActived("dev-test");
        assertEquals(2, employees.size());
    }

    /**
     * TODO: Method description
     */
    @Test
    public void testFindByEmailAndActived_FoundZero_thenSuccess()
    {
        List<Employee> employees = employeeRepository.findByEmailAndActived("DEV-test");
        assertEquals(0, employees.size());
    }
}

