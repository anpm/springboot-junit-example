/*
 * Copyright (c) 2019 Thermo Fisher Scientific
 * All rights reserved.
 */


package personal.springboot.example.service.impl;


import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import personal.springboot.example.ei.EditEmployeeEI;
import personal.springboot.example.ei.EmployeeEI;
import personal.springboot.example.entity.Employee;
import personal.springboot.example.service.EmployeeEIConverter;


/**
 * TODO: Class description
 */
@Service
@Validated
public class EmployeeEIConverterImpl implements EmployeeEIConverter
{
    @Override
    public EmployeeEI fromEntity(Employee employee)
    {
        return EmployeeEI.builder().id(employee.getId()).firstName(employee.getFirstName()).lastName(employee.getLastName()).email(employee.getEmail()).build();
    }

    @Override
    public Employee toEntity(EditEmployeeEI createEmployeeEI)
    {
        return Employee.builder().firstName(createEmployeeEI.getFirstName()).lastName(createEmployeeEI.getLastName()).email(createEmployeeEI.getEmail()).active(createEmployeeEI.isActive()).build();
    }
}

