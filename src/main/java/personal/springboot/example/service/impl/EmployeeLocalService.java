/*
 * Copyright (c) 2019 Thermo Fisher Scientific
 * All rights reserved.
 */


package personal.springboot.example.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import personal.springboot.example.ei.EditEmployeeEI;
import personal.springboot.example.ei.EmployeeEI;
import personal.springboot.example.entity.Employee;
import personal.springboot.example.repository.EmployeeRepository;
import personal.springboot.example.service.EmployeeEIConverter;
import personal.springboot.example.service.EmployeeService;


/**
 * TODO: Class description
 */
@Service
@Transactional
public class EmployeeLocalService implements EmployeeService
{
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeEIConverter employeeEIConverter;

    @Override
    public EmployeeEI find(long id)
    {
        Employee employee = employeeRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        return employeeEIConverter.fromEntity(employee);
    }

    @Override
    public EmployeeEI create(EditEmployeeEI editEmployeeEI)
    {
        Employee employee = employeeEIConverter.toEntity(editEmployeeEI);
        employeeRepository.save(employee);

        return employeeEIConverter.fromEntity(employee);
    }

    @Override
    public List<EmployeeEI> searchByEmail(String email)
    {
        return employeeRepository.findByEmailAndActived(email).stream().map(employeeEIConverter::fromEntity).collect(Collectors.toList());
    }
}

