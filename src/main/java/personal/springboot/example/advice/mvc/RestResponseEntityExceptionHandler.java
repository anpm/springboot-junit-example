/*
 * Copyright (c) 2019 Thermo Fisher Scientific
 * All rights reserved.
 */


package personal.springboot.example.advice.mvc;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import javax.validation.ConstraintViolationException;

import org.hibernate.validator.internal.engine.path.PathImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import personal.springboot.example.ei.RequestFailureEI;


/**
 * TODO: Class description
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler
{
    private static final Logger LOGGER = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

    /**
     * TODO: Method description
     *
     * @param exception
     * @param request
     *
     * @return
     */
    @ExceptionHandler(value = EntityNotFoundException.class)
    ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException exception, WebRequest request)
    {
        LOGGER.warn("Request '{}' responsed status: '{}'", request.getDescription(false), HttpStatus.NOT_FOUND);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(RequestFailureEI.builder().message("Resource not found").build());
    }

    /**
     * TODO: Method description
     *
     * @param exception
     * @param request
     *
     * @return
     */
    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException exception, WebRequest request)
    {
        LOGGER.warn("Request '{}' responsed status: '{}'", request.getDescription(false), HttpStatus.BAD_REQUEST);

        List<String> errorMessages = exception.getConstraintViolations().stream().map(each -> {
                                             PathImpl pathImpl = (PathImpl)each.getPropertyPath();
                                             return String.format("{%s} %s", pathImpl.getLeafNode().getName(), each.getMessage());
                                         }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RequestFailureEI.builder().messages(errorMessages).build());
    }

    /**
     * TODO: Method description
     *
     * @param exception
     * @param request
     *
     * @return
     */
    @ExceptionHandler({ Exception.class })
    public ResponseEntity<?> handleAll(Exception exception, WebRequest request)
    {
        LOGGER.error("Request '{}' responsed status: '{}'", request.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR, exception);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(RequestFailureEI.builder().message(exception.getMessage()).build());
    }
}

