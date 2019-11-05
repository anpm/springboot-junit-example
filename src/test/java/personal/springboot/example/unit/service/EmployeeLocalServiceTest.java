/*
 * Copyright (c) 2019 Thermo Fisher Scientific
 * All rights reserved.
 */


package personal.springboot.example.unit.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import personal.springboot.example.ei.EditEmployeeEI;
import personal.springboot.example.ei.EmployeeEI;
import personal.springboot.example.entity.Employee;
import personal.springboot.example.repository.EmployeeRepository;
import personal.springboot.example.service.EmployeeEIConverter;
import personal.springboot.example.service.EmployeeService;
import personal.springboot.example.service.impl.EmployeeLocalService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * TODO: Class description
 */
@RunWith(SpringRunner.class)
@MockBean({ EmployeeRepository.class, EmployeeEIConverter.class })
public class EmployeeLocalServiceTest
{
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeEIConverter employeeEIConverter;

    /**
     * TODO: Method description
     */
    @After
    public void teardown()
    {
        reset(employeeRepository);
        reset(employeeEIConverter);
    }

    /**
     * TODO: Method description
     */
    @Test(expected = EntityNotFoundException.class)
    public void testFind_whenEmployeeIdNotFound_thenThrowEntityNotFoundException()
    {
        when(employeeRepository.findById(Long.MIN_VALUE)).thenThrow(new EntityNotFoundException());
        employeeService.find(Long.MIN_VALUE);
    }

    /**
     * TODO: Method description
     */
    @Test
    public void testFind_withExistingEmployeeId_thenSuccess()
    {
        when(employeeRepository.findById(Long.MIN_VALUE)).thenReturn(Optional.of(new Employee()));
        when(employeeEIConverter.fromEntity(any())).thenReturn(new EmployeeEI());

        employeeService.find(Long.MIN_VALUE);

        verify(employeeRepository, times(1)).findById(Long.MIN_VALUE);
        verify(employeeEIConverter, times(1)).fromEntity(any(Employee.class));
    }

    /**
     * TODO: Method description
     */
    @Test
    public void testCreate_thenSuccess()
    {
        when(employeeEIConverter.toEntity(any())).thenReturn(new Employee());
        when(employeeRepository.save(any())).thenReturn(new Employee());
        when(employeeEIConverter.fromEntity(any())).thenReturn(new EmployeeEI());

        employeeService.create(new EditEmployeeEI());

        verify(employeeEIConverter, times(1)).toEntity(any(EditEmployeeEI.class));
        verify(employeeRepository, times(1)).save(any(Employee.class));
        verify(employeeEIConverter, times(1)).fromEntity(any(Employee.class));
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
        public EmployeeService employeeService()
        {
            return new EmployeeLocalService();
        }
    }
}

