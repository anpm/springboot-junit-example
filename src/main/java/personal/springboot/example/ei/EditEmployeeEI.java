/*
 * Copyright (c) 2019 Thermo Fisher Scientific
 * All rights reserved.
 */


package personal.springboot.example.ei;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * TODO: Class description
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditEmployeeEI
{
    @NotBlank
    private String firstName;

    private String lastName;

    @NotBlank
    private String email;

    private boolean active;
}

