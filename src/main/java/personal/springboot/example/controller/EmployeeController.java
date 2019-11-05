/*
 * Copyright (c) 2019 Thermo Fisher Scientific
 * All rights reserved.
 */


package personal.springboot.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import personal.springboot.example.ei.EditEmployeeEI;
import personal.springboot.example.service.EmployeeService;


/**
 * TODO: Class description
 */
@RestController
@RequestMapping(value = "/employee")
public class EmployeeController
{
    @Autowired
    private EmployeeService employeeService;

    /**
     * TODO: Method description
     *
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable(
        name = "id",
        required = true
    ) long id)
    {
        return ResponseEntity.ok(employeeService.find(id));
    }

    /**
     * TODO: Method description
     *
     *
     * @param email
     *
     * @return
     */
    @GetMapping()
    public ResponseEntity<?> search(@RequestParam(name = "email") String email)
    {
        return ResponseEntity.ok(employeeService.searchByEmail(email));
    }

    /**
     * TODO: Method description
     *
     *
     *
     * @param editEmployeeEI
     *
     * @return
     */
    @PostMapping
    public ResponseEntity<?> create(@RequestBody EditEmployeeEI editEmployeeEI)
    {
        return ResponseEntity.ok(employeeService.create(editEmployeeEI));
    }
}

