/*
 * Copyright (c) 2019 Thermo Fisher Scientific
 * All rights reserved.
 */


package personal.springboot.example.unit.controller;

import javax.persistence.EntityNotFoundException;

import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import personal.springboot.example.controller.EmployeeController;
import personal.springboot.example.ei.EmployeeEI;
import personal.springboot.example.service.EmployeeService;

import static org.hamcrest.CoreMatchers.equalTo;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * TODO: Class description
 */
@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
@MockBean({ EmployeeService.class })
public class EmployeeControllerTest
{
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private MockMvc mockMvc;

    /**
     * TODO: Method description
     */
    @After
    public void teardown()
    {
        reset(employeeService);
    }

    /**
     * TODO: Method description
     * @throws Exception
     */
    @Test
    public void testGet_WithExistingId_ThenSuccess() throws Exception
    {
        Long mockId = Long.MIN_VALUE;
        String mockEmail = "dev-test@gmail.com";
        String mockFirstName = "Dev";
        String mockLastName = "Test";

        EmployeeEI employeeEI = EmployeeEI.builder().id(mockId).email(mockEmail).firstName(mockFirstName).lastName(mockLastName).build();

        when(employeeService.find(mockId)).thenReturn(employeeEI);

        mockMvc
        .perform(get("/employee/" + mockId).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", equalTo(mockId)))
        .andExpect(jsonPath("$.email", equalTo(mockEmail)))
        .andExpect(jsonPath("$.firstName", equalTo(mockFirstName)))
        .andExpect(jsonPath("$.lastName", equalTo(mockLastName)));

        verify(employeeService, times(1)).find(mockId);
    }

    /**
     * TODO: Method description
     *
     * @throws Exception
     */
    @Test
    public void testGet_WithNoneExistingId_ThenFail() throws Exception
    {
        Long mockId = Long.MIN_VALUE;
        String mockErrorMessage = "Resource not found";
        when(employeeService.find(mockId)).thenThrow(new EntityNotFoundException(mockErrorMessage));

        mockMvc.perform(get("/employee/" + mockId).contentType(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError()).andExpect(jsonPath("$.message", equalTo(mockErrorMessage)));

        verify(employeeService, times(1)).find(mockId);
    }
}

