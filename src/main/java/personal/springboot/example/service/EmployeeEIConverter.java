/*
 * Copyright (c) 2019 Thermo Fisher Scientific
 * All rights reserved.
 */


package personal.springboot.example.service;

import javax.validation.Valid;

import personal.springboot.example.ei.EditEmployeeEI;
import personal.springboot.example.ei.EmployeeEI;
import personal.springboot.example.entity.Employee;


/**
 * TODO: Interface description
 *
 */
public interface EmployeeEIConverter
{
    /**
     * TODO: Method description
     *
     * @param employee
     *
     * @return
     */
    @Valid
    EmployeeEI fromEntity(Employee employee);

    /**
     * TODO: Method description
     *
     *
     *
     *
     * @param editEmployeeEI
     *
     * @return
     */
    Employee toEntity(@Valid EditEmployeeEI editEmployeeEI);
}

