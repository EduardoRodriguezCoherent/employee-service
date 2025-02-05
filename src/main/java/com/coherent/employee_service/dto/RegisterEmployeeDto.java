package com.coherent.employee_service.dto;

import com.coherent.employee_service.model.EmployeeRole;

import java.time.LocalDate;
import java.util.Set;

public record RegisterEmployeeDto(
        String name,
        String surname,
        LocalDate birthDate,
        String bankAccount,
        long salary,
        Set<EmployeeRole> roles,
        Set<Long> expertiseAreas
) {}