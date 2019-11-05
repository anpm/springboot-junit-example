/*
 * Copyright (c) 2019 Thermo Fisher Scientific
 * All rights reserved.
 */


package personal.springboot.example.ei;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;


/**
 * TODO: Class description
 */
@Data
@Builder
@JsonInclude(value = Include.NON_NULL)
public class RequestFailureEI
{
    private String message;
    private List<String> messages;
}

