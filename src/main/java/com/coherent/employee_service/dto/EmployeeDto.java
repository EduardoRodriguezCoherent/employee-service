package com.coherent.employee_service.dto;

import com.coherent.employee_service.model.EmployeeRole;

import java.time.LocalDate;
import java.util.Set;

public record EmployeeDto(
        Long id,
        String name,
        String lastName,
        LocalDate birthDate,
        Set<EmployeeRole> roles,
        Set<Long> expertiseAreas // Facility IDs
) {
}