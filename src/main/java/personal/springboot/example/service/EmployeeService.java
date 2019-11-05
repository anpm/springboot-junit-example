/*
 * Copyright (c) 2019 Thermo Fisher Scientific
 * All rights reserved.
 */


package personal.springboot.example.service;

import java.util.List;

import personal.springboot.example.ei.EditEmployeeEI;
import personal.springboot.example.ei.EmployeeEI;


/**
 * TODO: Interface description
 *
 */
public interface EmployeeService
{
    /**
     * TODO: Method description
     *
     * @param id
     *
     * @return
     *
     */
    EmployeeEI find(long id);

    /**
     * TODO: Method description
     *
     *
     * @param createEmployeeEI
     *
     * @return
     *
     */
    EmployeeEI create(EditEmployeeEI createEmployeeEI);

    /**
     * TODO: Method description
     *
     *
     * @param email
     *
     * @return
     */
    List<EmployeeEI> searchByEmail(String email);

}

