/*
 * Copyright (c) 2019 Thermo Fisher Scientific
 * All rights reserved.
 */


package personal.springboot.example.unit.service;

import javax.validation.ConstraintViolationException;

import org.junit.runner.RunWith;

import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import personal.springboot.example.ei.EditEmployeeEI;
import personal.springboot.example.ei.EmployeeEI;
import personal.springboot.example.entity.Employee;
import personal.springboot.example.service.EmployeeEIConverter;
import personal.springboot.example.service.impl.EmployeeEIConverterImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


/**
 * TODO: Class description
 */
@RunWith(SpringRunner.class)
public class EmployeeEIConverterImplTest
{
    @Autowired
    private EmployeeEIConverter employeeEIConverter;

    @Value("${abc.test.data}")
    private String data;

    /**
     * TODO: Method description
     */
    @Test
    public void testFromEntity_thenSuccess()
    {
        Long mockId = Long.MIN_VALUE;
        String mockEmail = "dev-test@gmail.com";
        String mockFirstName = "Dev";
        String mockLastName = "Test";

        EmployeeEI employeeEI = employeeEIConverter.fromEntity(Employee.builder().id(mockId).email(mockEmail).firstName(mockFirstName).lastName(mockLastName).build());

        assertEquals(mockId, employeeEI.getId());
        assertEquals(mockEmail, employeeEI.getEmail());
        assertEquals(mockFirstName, employeeEI.getFirstName());
        assertEquals(mockLastName, employeeEI.getLastName());
    }

    /**
     * TODO: Method description
     */
    @Test(expected = ConstraintViolationException.class)
    public void testFromEntity_EmailIsEmpty_thenFail()
    {
        Long mockId = Long.MIN_VALUE;
        String mockEmail = "";
        String mockFirstName = "Dev";
        String mockLastName = "Test";

        employeeEIConverter.fromEntity(Employee.builder().id(mockId).email(mockEmail).firstName(mockFirstName).lastName(mockLastName).build());
    }

    /**
     * TODO: Method description
     */
    @Test(expected = ConstraintViolationException.class)
    public void testFromEntity_FirtNameIsEmpty_thenFail()
    {
        Long mockId = Long.MIN_VALUE;
        String mockEmail = "dev-test@gmail.com";
        String mockFirstName = "";
        String mockLastName = "Test";

        employeeEIConverter.fromEntity(Employee.builder().id(mockId).email(mockEmail).firstName(mockFirstName).lastName(mockLastName).build());
    }

    /**
     * TODO: Method description
     */
    @Test
    public void testToEntity_thenSuccess()
    {
        String mockEmail = "dev-test@gmail.com";
        String mockFirstName = "Dev";
        String mockLastName = "Test";

        EditEmployeeEI editEmployeeEI = EditEmployeeEI.builder().firstName(mockFirstName).lastName(mockLastName).email(mockEmail).build();

        Employee employee = employeeEIConverter.toEntity(editEmployeeEI);

        assertNull(employee.getId());
        assertEquals(mockEmail, editEmployeeEI.getEmail());
        assertEquals(mockFirstName, editEmployeeEI.getFirstName());
        assertEquals(mockLastName, editEmployeeEI.getLastName());
    }

    /**
     * TODO: Method description
     */
    @Test(expected = ConstraintViolationException.class)
    public void testToEntity_FirtNameIsEmpty_thenFail()
    {
        String mockEmail = "dev-test@gmail.com";
        String mockFirstName = "";
        String mockLastName = "Test";

        EditEmployeeEI editEmployeeEI = EditEmployeeEI.builder().firstName(mockFirstName).lastName(mockLastName).email(mockEmail).build();

        employeeEIConverter.toEntity(editEmployeeEI);
    }

    /**
     * TODO: Method description
     */
    @Test(expected = ConstraintViolationException.class)
    public void testToEntity_EmailIsEmpty_thenFail()
    {
        String mockEmail = "";
        String mockFirstName = "Dev";
        String mockLastName = "Test";

        EditEmployeeEI editEmployeeEI = EditEmployeeEI.builder().firstName(mockFirstName).lastName(mockLastName).email(mockEmail).build();

        employeeEIConverter.toEntity(editEmployeeEI);
    }


    /**
     * TODO: Class description
     */

    @TestConfiguration
    static class TestConfig
    {
        /**
         * TODO: Method description
         *
         * @return
         */
        @Bean
        public MethodValidationPostProcessor methodValidationPostProcessor()
        {
            return new MethodValidationPostProcessor();
        }

        /**
         * TODO: Method description
         *
         * @return
         */
        @Bean
        public EmployeeEIConverter employeeEIConverter()
        {
            return new EmployeeEIConverterImpl();
        }
    }
}

